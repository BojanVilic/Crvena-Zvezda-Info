package com.bojanvilic.crvenazvezdainfo.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bojanvilic.crvenazvezdainfo.R

enum class TopLevelDestinations(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    val route: String
) {
    Home(
        icon = R.drawable.ic_menu_home_page,
        title = R.string.menu_home,
        route = "home_route"
    ),
    Football(
        icon = R.drawable.ic_football,
        title = R.string.menu_football,
        route = "football_route"
    ),
    Basketball(
        icon = R.drawable.ic_basketball,
        title = R.string.menu_basketball,
        route = "basketball_route"
    ),
    Other(
        icon = R.drawable.ic_menu_other_page,
        title = R.string.menu_other,
        route = "other_route"
    ),
    Serbia(
        icon = R.drawable.ic_menu_serbia_page,
        title = R.string.menu_serbia,
        route = "serbia_route"
    );

    companion object {
        fun fromRoute(route: String?): TopLevelDestinations? =
            when (route?.substringBefore("/")) {
                Home.route -> Home
                Football.route -> Football
                Basketball.route -> Basketball
                Other.route -> Other
                Serbia.route -> Serbia
                "root_graph" -> Home
                null -> Home
                else -> null
            }
    }
}