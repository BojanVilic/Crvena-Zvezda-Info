package com.bojanvilic.crvenazvezdainfo.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bojanvilic.crvenazvezdainfo.ui.components.ArticleDetailsScreen

const val articleDetailsRoute = "article_details"
const val articleDetailsId = "articleId"

fun NavGraphBuilder.articleDetailsScreen(onBackClicked: () -> Unit) {
    composable(
        route = "$articleDetailsRoute/{$articleDetailsId}",
        arguments = listOf(navArgument(articleDetailsId) { type = NavType.StringType })
    ) {
        ArticleDetailsScreen(onBackClicked = onBackClicked)
    }
}