@file:OptIn(ExperimentalMaterial3Api::class)

package com.bojanvilic.crvenazvezdainfo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme
import com.bojanvilic.crvenazvezdainfo.ui.components.DetailsTopBar
import com.bojanvilic.crvenazvezdainfo.ui.components.TabRow
import com.bojanvilic.crvenazvezdainfo.ui.navigation.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrvenaZvezdaInfoApp()
        }
    }
}

@Composable
fun CrvenaZvezdaInfoApp() {
    AppTheme {
        val allScreens = TopLevelDestinations.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen: TopLevelDestinations? = TopLevelDestinations.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            topBar = {
                if (currentScreen != null) {
                    TabRow(
                        allScreens = allScreens,
                        onTabSelected = { screen ->
                            navController.navigate(screen.route)
                        },
                        currentScreen = currentScreen
                    )
                } else {
                    DetailsTopBar(
                        onBackClicked = {
                            navController.navigateUp()
                        })
                }
            }
        ) { innerPadding ->
            AppNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TopLevelDestinations.Home.route,
        modifier = modifier
    ) {
        homeScreen(navController)
        footballScreen(navController)
        basketballScreen(navController)
        otherScreen(navController)
        serbiaScreen(navController)
        articleDetailsScreen()
    }
}
