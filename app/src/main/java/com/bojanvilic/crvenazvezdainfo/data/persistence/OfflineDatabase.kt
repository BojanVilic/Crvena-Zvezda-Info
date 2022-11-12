package com.bojanvilic.crvenazvezdainfo.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ArticleModelRoom::class],
    version = 2,
    exportSchema = false)
abstract class OfflineDatabase : RoomDatabase() {

    abstract fun roomArticleDao() : ArticleDao
}