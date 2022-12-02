package com.bojanvilic.crvenazvezdainfo.injection

import androidx.lifecycle.SavedStateHandle
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import com.bojanvilic.crvenazvezdainfo.ui.ArticleDetailsViewModel
import com.bojanvilic.crvenazvezdainfo.ui.ArticlesViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun getArticlesViewModel(
        articleRepository: ArticleRepository,
        savedStateHandle: SavedStateHandle
    ): ArticlesViewModel {
        return ArticlesViewModel(articleRepository, savedStateHandle)
    }

    @Provides
    fun getArticleDetailsViewModel(
        articleRepository: ArticleRepository,
        savedStateHandle: SavedStateHandle
    ): ArticleDetailsViewModel {
        return ArticleDetailsViewModel(articleRepository, savedStateHandle)
    }
}