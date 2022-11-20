package com.bojanvilic.crvenazvezdainfo.injection

import androidx.lifecycle.SavedStateHandle
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import com.bojanvilic.crvenazvezdainfo.ui.ArticlesViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun getHomeViewModel(
        articleRepository: ArticleRepository
    ): HomeViewModel {
        return HomeViewModel(articleRepository)
    }

    @Provides
    fun getArticlesViewModel(
        articleRepository: ArticleRepository,
        savedStateHandle: SavedStateHandle
    ): ArticlesViewModel {
        return ArticlesViewModel(articleRepository, savedStateHandle)
    }
}