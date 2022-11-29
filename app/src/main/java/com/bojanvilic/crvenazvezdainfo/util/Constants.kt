package com.bojanvilic.crvenazvezdainfo.util

import androidx.datastore.preferences.core.longPreferencesKey

const val BASE_URL = "https://www.crvenazvezdainfo.com/wp-json/wp/v2/"
const val OFFLINE_DATABASE = "offline_articles"
const val savedStateCategory = "category"
const val REFRESH_INTERVAL = 900000L
const val USER_PREFERENCES = "user_preferences"
val HOME_PAGE_LAST_REFRESHED_KEY = longPreferencesKey("home_page_last_refreshed_key")
val FOOTBALL_PAGE_LAST_REFRESHED_KEY = longPreferencesKey("football_page_last_refreshed_key")
val BASKETBALL_PAGE_LAST_REFRESHED_KEY = longPreferencesKey("basketball_page_last_refreshed_key")
val OTHER_PAGE_LAST_REFRESHED_KEY = longPreferencesKey("other_page_last_refreshed_key")
val SERBIA_PAGE_LAST_REFRESHED_KEY = longPreferencesKey("serbia_page_last_refreshed_key")