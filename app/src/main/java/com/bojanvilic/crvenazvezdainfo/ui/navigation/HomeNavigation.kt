package com.bojanvilic.crvenazvezdainfo.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.crvenazvezdainfo.ui.ArticlesViewModel
import com.bojanvilic.crvenazvezdainfo.ui.TopLevelDestinations
import com.bojanvilic.crvenazvezdainfo.ui.components.ArticlesScreen
import com.bojanvilic.crvenazvezdainfo.util.Category

fun NavGraphBuilder.homeScreen(navController: NavHostController) {
    composable(TopLevelDestinations.Home.route) {
        val articlesViewModel: ArticlesViewModel = hiltViewModel()
        articlesViewModel.setCurrentCategory(Category.HOME.categoryIntValue)
        ArticlesScreen(
            articlesViewModel = articlesViewModel,
            onArticleClicked = { articleId ->
                navController.navigate("$articleDetailsRoute/$articleId")
            }
        )
    }
}