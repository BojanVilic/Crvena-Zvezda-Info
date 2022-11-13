package com.bojanvilic.crvenazvezdainfo.repositories

import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.util.NetworkBoundResource
import com.bojanvilic.crvenazvezdainfo.util.NetworkStatusTracker
import com.bojanvilic.crvenazvezdainfo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.function.Function
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    val articleDao: ArticleDao,
    val articleWebService: ArticleWebService,
    val networkStatusTracker: NetworkStatusTracker
) {

    suspend fun getLatestArticles(): Flow<Resource<List<ArticleModelRoom>>> {
        return flow {
            object : NetworkBoundResource<List<Model.Article>, List<ArticleModelRoom>>(networkStatusTracker, this) {
                override fun getRemote(): suspend () -> List<Model.Article> {
                    return { articleWebService.getArticlesList() }
                }

                override fun getLocal(): Flow<List<ArticleModelRoom>> {
                    return articleDao.getRecommendedArticles()
                }

                override fun saveCallResult(localType: List<ArticleModelRoom>) {
                    articleDao.insertAll(localType)
                }

                override fun mapper(): Function<List<Model.Article>, List<ArticleModelRoom>> {
                    return Function {
                        val result: MutableList<ArticleModelRoom> = ArrayList()
                        for (element in it) {
                            result.add(
                                ArticleModelRoom(
                                    element.id,
                                    element.date,
                                    element.title.title,
                                    element.content.article_text,
                                    element._embedded.wpFeaturedmedia[0].source_url,
                                    element.categories[0].toString()
                                )
                            )
                        }
                        result
                    }
                }

            }
        }
    }
}