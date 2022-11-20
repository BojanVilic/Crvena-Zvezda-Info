package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bojanvilic.crvenazvezdainfo.theme.AppTheme
import com.bojanvilic.crvenazvezdainfo.ui.ArticlesViewModel
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.ui.components.ArticleDetailsScreen
import com.bojanvilic.crvenazvezdainfo.ui.components.ArticlesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment(), IViewContract.View {

    private val articlesViewModel by viewModels<ArticlesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            ArticlesScreen(
                                articlesViewModel,
                                onArticleClicked = {
                                    articlesViewModel.setArticleId(it.toString())
                                    navController.navigate("article_details")
                                }
                            )
                        }
                        composable("article_details") {
                            ArticleDetailsScreen(
                                articlesViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}