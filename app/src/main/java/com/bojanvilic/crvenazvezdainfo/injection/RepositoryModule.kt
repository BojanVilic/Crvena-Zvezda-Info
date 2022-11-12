package com.bojanvilic.crvenazvezdainfo.injection

import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun getArticlesRepository(
        articleDao: ArticleDao,
        articleWebService: ArticleWebService
    ): ArticleRepository {
        return ArticleRepository(articleDao, articleWebService)
    }
}