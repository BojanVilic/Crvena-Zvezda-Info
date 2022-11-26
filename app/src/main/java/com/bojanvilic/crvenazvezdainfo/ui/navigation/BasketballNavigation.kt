package com.bojanvilic.crvenazvezdainfo.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bojanvilic.crvenazvezdainfo.ui.TopLevelDestinations
import com.bojanvilic.crvenazvezdainfo.ui.components.ArticlesScreen

fun NavGraphBuilder.basketballScreen(navController: NavHostController) {
    composable(TopLevelDestinations.Basketball.route) {
        ArticlesScreen(
            onArticleClicked = { articleId ->
                navController.navigate("$articleDetailsRoute/$articleId")
            }
        )
    }
}