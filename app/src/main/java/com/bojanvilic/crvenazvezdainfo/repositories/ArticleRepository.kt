package com.bojanvilic.crvenazvezdainfo.repositories

import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.util.NetworkBoundResource
import com.bojanvilic.crvenazvezdainfo.util.NetworkStatusTracker
import com.bojanvilic.crvenazvezdainfo.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.function.Function
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    val articleDao: ArticleDao,
    val articleWebService: ArticleWebService,
    val networkStatusTracker: NetworkStatusTracker
) {

    fun getLatestArticles(): Flow<Resource<List<ArticleModelRoom>>> {
        return object : NetworkBoundResource<List<Model.Article>, List<ArticleModelRoom>>(networkStatusTracker) {
            override fun getRemote(): suspend () -> List<Model.Article> {
                return {
                    withContext(Dispatchers.IO) {
                        articleWebService.getArticlesList()
                    }
                }
            }

            override fun getLocal(): Flow<List<ArticleModelRoom>> {
                return articleDao.getAllArticlesPaged()
            }

            override suspend fun saveCallResult(data: List<ArticleModelRoom>) {
                withContext(Dispatchers.IO) {
                    articleDao.insertAll(data)
                }
            }

            override fun mapper(): Function<List<Model.Article>, List<ArticleModelRoom>> {
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
        return object : NetworkBoundResource<Model.Article, ArticleModelRoom>(networkStatusTracker) {
            override fun getRemote(): suspend () -> Model.Article {
                return {
                    withContext(Dispatchers.IO) {
                        articleWebService.getArticleDetails(articleId)
                    }
                }
            }

            override fun getLocal(): Flow<ArticleModelRoom> {
                return articleDao.getNoteById(articleId)
            }

            override suspend fun saveCallResult(data: ArticleModelRoom) {
                withContext(Dispatchers.IO) {
                    articleDao.insert(data)
                }
            }

            override fun mapper(): Function<Model.Article, ArticleModelRoom> {
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