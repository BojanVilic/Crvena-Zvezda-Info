package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import com.bojanvilic.crvenazvezdainfo.util.Resource
import com.bojanvilic.crvenazvezdainfo.util.savedStateCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArticlesViewModel @Inject
constructor(
    articleRepository: ArticleRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var pager = MutableStateFlow(1)

    var articlesList: Flow<Resource<List<ArticleModelRoom>>> =
        savedStateHandle.getStateFlow(savedStateCategory, initialValue = 0)
            .flatMapLatest { category ->
                pager.flatMapLatest { pageNumber ->
                    articleRepository.getLatestArticlesByCategory(category, pageNumber)
                }
            }

    fun setCurrentCategory(categoryIntValue: Int) {
        savedStateHandle[savedStateCategory] = categoryIntValue
    }

    fun fetchNextPage() {
        Timber.d("PROBA: ${pager.value}")
        pager.tryEmit(pager.value+1)
    }
}