package com.bojanvilic.crvenazvezdainfo.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.bojanvilic.crvenazvezdainfo.R

enum class AppScreen(
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    Home(
        icon = R.drawable.ic_menu_home_page,
        title = R.string.menu_home
    ),
    Football(
        icon = R.drawable.ic_football,
        title = R.string.menu_football
    ),
    Basketball(
        icon = R.drawable.ic_basketball,
        title = R.string.menu_basketball
    ),
    Other(
        icon = R.drawable.ic_menu_other_page,
        title = R.string.menu_other
    ),
    Serbia(
        icon = R.drawable.ic_menu_serbia_page,
        title = R.string.menu_serbia
    );

    companion object {
        fun fromRoute(route: String?): AppScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Football.name -> Football
                Basketball.name -> Basketball
                Other.name -> Other
                Serbia.name -> Serbia
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}