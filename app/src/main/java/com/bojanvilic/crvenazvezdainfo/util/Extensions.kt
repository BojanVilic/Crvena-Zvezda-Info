package com.bojanvilic.crvenazvezdainfo.util

import android.content.Context
import android.content.Intent
import android.text.Html
import androidx.compose.foundation.lazy.LazyListState
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.bojanvilic.crvenazvezdainfo.R
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


fun String?.formatDateForArticle(): String? {
    this?.let {
        try {
            val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMANY)
            val calendar: Calendar = GregorianCalendar()
            val localDateTime: LocalDateTime = LocalDateTime.parse(it, dtf).plusHours(TimeUnit.HOURS.convert(calendar.timeZone.rawOffset.toLong(), TimeUnit.MILLISECONDS))
            return if (localDateTime.isDateToday()) "Danas u ${localDateTime.format(DateTimeFormatter.ofPattern("HH:mm", Locale.GERMANY))}" else localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.GERMANY))
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }
    return this
}

fun LocalDateTime.isDateToday(): Boolean {
    val today = LocalDateTime.now()
    return this.year == today.year && this.monthValue == today.monthValue && this.dayOfMonth == today.dayOfMonth
}

fun String?.toHtmlString(): String {
    return Html.fromHtml(this, 0).toString()
}

fun String?.categoryNumberToStringResource(): Int {
    return when(this) {
        "1" -> R.string.menu_football
        "3" -> R.string.menu_other
        "4" -> R.string.menu_serbia
        "5" -> R.string.menu_basketball
        else -> R.string.menu_other
    }
}

fun LazyListState.isScrolledToTheEnd() : Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastItem == null || lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
}

fun Context.shareArticleLink(link: String) {
    try {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_SUBJECT,
            this.getString(R.string.app_name)
        )
        shareIntent.putExtra(Intent.EXTRA_TEXT, link)
        ContextCompat.startActivity(this, Intent.createChooser(shareIntent, this.getString(R.string.share_chose_app_label)), null)
    } catch (e: Exception) {
        Timber.e(e.message)
    }
}

suspend fun Int.updateLastRefreshedIntervals(dataStore: DataStore<Preferences>) {
    val currentTime = Calendar.getInstance().timeInMillis
    dataStore.edit { settings ->
        settings[this.dataStoreKeyMapper()] = currentTime
    }
}

fun Int.dataStoreKeyMapper(): Preferences.Key<Long> {
    return when (this) {
        0 -> HOME_PAGE_LAST_REFRESHED_KEY
        1 -> FOOTBALL_PAGE_LAST_REFRESHED_KEY
        5 -> BASKETBALL_PAGE_LAST_REFRESHED_KEY
        3 -> OTHER_PAGE_LAST_REFRESHED_KEY
        4 -> SERBIA_PAGE_LAST_REFRESHED_KEY
        else -> HOME_PAGE_LAST_REFRESHED_KEY
    }
}