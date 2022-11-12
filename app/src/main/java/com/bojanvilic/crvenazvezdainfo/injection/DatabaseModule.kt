package com.bojanvilic.crvenazvezdainfo.injection

import android.content.Context
import androidx.room.Room
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.data.persistence.OfflineDatabase
import com.bojanvilic.crvenazvezdainfo.util.OFFLINE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun getRoomArticleDao(offlineDatabase: OfflineDatabase): ArticleDao {
        return offlineDatabase.roomArticleDao()
    }

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext appContext: Context): OfflineDatabase {
        return Room.databaseBuilder(appContext, OfflineDatabase::class.java, OFFLINE_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
}