package com.bojanvilic.crvenazvezdainfo.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.ArticleEntity
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import java.util.function.Function
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val articleWebService: ArticleWebService,
    private val networkStatusTracker: NetworkStatusTracker,
    private val dataStore: DataStore<Preferences>
) {

    fun getLatestArticlesByCategory(category: Int, pageNumber: Int): Flow<Resource<List<ArticleModelRoom>>> {
        return object : NetworkBoundResource<List<ArticleEntity>, List<ArticleModelRoom>>(networkStatusTracker) {
            override suspend fun shouldFetchFromRemote(): suspend () -> Boolean {
                return {
                    if (pageNumber == 1) {
                        withContext(Dispatchers.IO) {
                            val lastUpdatedTime = dataStore.data.map {
                                it[category.dataStoreKeyMapper()] ?: 0
                            }.first()

                            Calendar.getInstance().timeInMillis - lastUpdatedTime > REFRESH_INTERVAL
                        }
                    } else {
                        true
                    }
                }
            }

            override fun getRemote(): suspend () -> List<ArticleEntity> {
                return {
                    withContext(Dispatchers.IO) {
                        if (category == 0) {
                            articleWebService.getMostRecentArticlesList(pageNumber = pageNumber)
                        } else {
                            articleWebService.getArticlesListByCategory(category = category, pageNumber = pageNumber)
                        }
                    }
                }
            }

            override fun getLocal(): Flow<List<ArticleModelRoom>> {
                return if (category == 0) {
                    articleDao.getAllArticlesPaged(pageNumber*ARTICLES_PER_PAGE)
                } else {
                    articleDao.getArticlesByCategory(category.toString(), pageNumber*ARTICLES_PER_PAGE)
                }
            }

            override suspend fun saveCallResult(data: List<ArticleModelRoom>) {
                withContext(Dispatchers.IO) {
                    category.updateLastRefreshedIntervals(dataStore)
                    articleDao.insertAll(data)
                }
            }

            override fun mapper(): Function<List<ArticleEntity>, List<ArticleModelRoom>> {
                return Function {
                    val result: MutableList<ArticleModelRoom> = ArrayList()
                    for (element in it) {
                        result.add(
                            ArticleModelRoom(
                                id = element.id,
                                date = element.date,
                                title = element.title.title,
                                content = element.content.article_text,
                                imageUrl = element._embedded.wpFeaturedmedia[0].source_url,
                                category = element.categories[0].toString(),
                                excerpt = element.excerpt.rendered
                            )
                        )
                    }
                    result
                }
            }
        }.asFlow()
    }

    fun getArticleDetails(articleId: String): Flow<Resource<ArticleModelRoom>> {
        return object : NetworkBoundResource<ArticleEntity, ArticleModelRoom>(networkStatusTracker) {
            override suspend fun shouldFetchFromRemote(): suspend () -> Boolean {
                return {
                    false
                }
            }

            override fun getRemote(): suspend () -> ArticleEntity {
                return {
                    withContext(Dispatchers.IO) {
                        articleWebService.getArticleDetails(articleId)
                    }
                }
            }

            override fun getLocal(): Flow<ArticleModelRoom> {
                return articleDao.getArticleById(articleId)
            }

            override suspend fun saveCallResult(data: ArticleModelRoom) {
                withContext(Dispatchers.IO) {
                    articleDao.insert(data)
                }
            }

            override fun mapper(): Function<ArticleEntity, ArticleModelRoom> {
                return Function {
                    ArticleModelRoom(
                        id = it.id,
                        date = it.date,
                        title = it.title.title,
                        content = it.content.article_text,
                        imageUrl = it._embedded.wpFeaturedmedia[0].source_url,
                        category = it.categories[0].toString(),
                        excerpt = it.excerpt.rendered
                    )
                }
            }
        }.asFlow()
    }

    fun recommendedArticles(currentArticleId: String) = articleDao.getRecommendedArticles(currentArticleId)
}