package com.bojanvilic.crvenazvezdainfo.util

import android.text.Html
import android.text.format.DateUtils
import com.bojanvilic.crvenazvezdainfo.R
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


fun String?.formatDateForArticle(): String? {
    this?.let {
        try {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMANY)
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY)
            val output: String = formatter.format(parser.parse(it)!!)
            val date: String = output.split("\\s+".toRegex())[0]
            val time: String = output.split("\\s+".toRegex())[1]
            return if (this.isDateToday()) "Danas u $time" else "$date $time"
        } catch (e: Exception) {
            Timber.e(e.message)
        }
    }
    return this
}

fun String?.isDateToday(): Boolean {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMANY)
    val localDate: LocalDateTime = LocalDateTime.parse(this, formatter)
    val timeInMilliseconds: Long = localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
    return DateUtils.isToday(timeInMilliseconds)
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