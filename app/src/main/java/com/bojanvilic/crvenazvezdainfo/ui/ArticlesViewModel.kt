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
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArticlesViewModel @Inject
constructor(
    private val articleRepository: ArticleRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val article: Flow<Resource<ArticleModelRoom>> =
        savedStateHandle.getStateFlow("id", initialValue = "50298")
            .flatMapLatest { id ->
                Timber.d(id)
                articleRepository.getArticleDetails(id)
            }

    var articlesList = articleRepository.getLatestArticles()

    fun setArticleId(id: String) {
        savedStateHandle["id"] = id
    }
}