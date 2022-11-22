package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import com.bojanvilic.crvenazvezdainfo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArticlesViewModel @Inject
constructor(
    private val articleRepository: ArticleRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var recommendedArticles: Flow<List<ArticleModelRoom>> = flow {
        listOf<ArticleModelRoom>()
    }

    val article: Flow<Resource<ArticleModelRoom>> =
        savedStateHandle.getStateFlow("id", initialValue = "0")
            .flatMapLatest { id ->
                Timber.d(id)
                recommendedArticles = articleRepository.recommendedArticles(id)
                articleRepository.getArticleDetails(id)
            }

    val articlesList = articleRepository.getLatestArticles()

    fun setArticleId(id: String) {
        savedStateHandle["id"] = id
    }
}