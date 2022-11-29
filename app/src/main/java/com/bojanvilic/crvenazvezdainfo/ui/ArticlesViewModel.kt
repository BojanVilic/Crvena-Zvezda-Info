package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import com.bojanvilic.crvenazvezdainfo.util.Resource
import com.bojanvilic.crvenazvezdainfo.util.savedStateCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArticlesViewModel @Inject
constructor(
    articleRepository: ArticleRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val articlesList: Flow<Resource<List<ArticleModelRoom>>> =
        savedStateHandle.getStateFlow(savedStateCategory, initialValue = 0)
            .flatMapLatest { category ->
                articleRepository.getLatestArticlesByCategory(category)
            }

    fun setCurrentCategory(categoryIntValue: Int) {
        savedStateHandle[savedStateCategory] = categoryIntValue
    }
}