package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject
constructor(
    articleRepository: ArticleRepository
): ViewModel() {

    var articlesList = articleRepository.getLatestArticles()
}