package com.bojanvilic.crvenazvezdainfo.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.ui.ArticlesViewModel
import com.bojanvilic.crvenazvezdainfo.util.Resource
import com.bojanvilic.crvenazvezdainfo.util.Status

@Composable
fun ArticleDetailsScreen(
    articlesViewModel: ArticlesViewModel = viewModel()
) {

    val articleDetails = articlesViewModel.article.collectAsState(initial = Resource.success(ArticleModelRoom()))

    if (articleDetails.value.status == Status.SUCCESS) {
        if (articleDetails.value.data.title.isNullOrEmpty().not()) {
            Text(text = articleDetails.value.data.title?: "Basic text")
        }
    }
}