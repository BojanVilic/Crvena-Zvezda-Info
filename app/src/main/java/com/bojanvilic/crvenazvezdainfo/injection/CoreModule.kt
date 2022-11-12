package com.bojanvilic.crvenazvezdainfo.injection

import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun getArticleWebService(): ArticleWebService {
        return ArticleWebService.create()
    }
}