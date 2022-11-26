package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ArticlesViewModel @Inject
constructor(
    articleRepository: ArticleRepository
): ViewModel() {

    val articlesList = articleRepository.getLatestArticles()
}