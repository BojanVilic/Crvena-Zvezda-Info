@file:OptIn(ExperimentalMaterial3Api::class)

package com.bojanvilic.crvenazvezdainfo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme
import com.bojanvilic.crvenazvezdainfo.ui.components.ArticlesScreen
import com.bojanvilic.crvenazvezdainfo.ui.components.TabRow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageActivity : ComponentActivity() {

    private val articlesViewModel by viewModels<ArticlesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrvenaZvezdaInfoApp(articlesViewModel)
        }
    }
}

@Composable
fun CrvenaZvezdaInfoApp(articlesViewModel: ArticlesViewModel) {
    AppTheme {
        val allScreens = AppScreen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = AppScreen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            topBar = {
                TabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            RallyNavHost(navController, modifier = Modifier.padding(innerPadding), articlesViewModel)
        }
    }
}

@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    articlesViewModel: ArticlesViewModel
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Home.name,
        modifier = modifier
    ) {
        composable(AppScreen.Home.name) {
            ArticlesScreen(
                articlesViewModel,
                onArticleClicked = {
                    articlesViewModel.setArticleId(it.toString())
                    navController.navigate("article_details")
                }
            )
        }
        composable(AppScreen.Football.name) {
            ArticlesScreen(
                articlesViewModel,
                onArticleClicked = {
                    articlesViewModel.setArticleId(it.toString())
                    navController.navigate("article_details")
                }
            )
        }
        composable(AppScreen.Basketball.name) {
            ArticlesScreen(
                articlesViewModel,
                onArticleClicked = {
                    articlesViewModel.setArticleId(it.toString())
                    navController.navigate("article_details")
                }
            )
        }
        composable(AppScreen.Other.name) {
            ArticlesScreen(
                articlesViewModel,
                onArticleClicked = {
                    articlesViewModel.setArticleId(it.toString())
                    navController.navigate("article_details")
                }
            )
        }
        composable(AppScreen.Serbia.name) {
            ArticlesScreen(
                articlesViewModel,
                onArticleClicked = {
                    articlesViewModel.setArticleId(it.toString())
                    navController.navigate("article_details")
                }
            )
        }
    }
}
