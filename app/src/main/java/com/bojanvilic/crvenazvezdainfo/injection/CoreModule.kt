package com.bojanvilic.crvenazvezdainfo.injection

import android.content.Context
import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import com.bojanvilic.crvenazvezdainfo.util.NetworkStatusTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun getNetworkStatusTracker(@ApplicationContext context: Context): NetworkStatusTracker {
        return NetworkStatusTracker(context)
    }
}