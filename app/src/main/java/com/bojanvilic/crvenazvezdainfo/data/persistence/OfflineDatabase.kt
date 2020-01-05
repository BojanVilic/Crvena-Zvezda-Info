package com.bojanvilic.crvenazvezdainfo.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


private const val OFFLINE_DATABASE = "offline_articles"

@Database(entities = [ArticleModelRoom::class],
    version = 2,
    exportSchema = false)
abstract class OfflineDatabase : RoomDatabase() {

    abstract fun roomArticleDao() : ArticleDao
}