package com.bojanvilic.crvenazvezdainfo.data.persistence

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert
    fun insert(articleRoomRoom: ArticleModelRoom) : Completable

    @Query("SELECT * FROM articles_table ORDER BY date DESC Limit 6")
    fun getRecommendedArticles() : Flow<List<ArticleModelRoom>>

    @Query("SELECT * FROM articles_table ORDER BY date DESC")
    fun getAllArticlesPaged() : DataSource.Factory<Int, ArticleModelRoom>

    @Query("SELECT * FROM articles_table WHERE id = :id")
    fun getNoteById(id: Int): ArticleModelRoom

    @Query("SELECT * FROM articles_table WHERE category = :category ORDER BY date DESC")
    fun getNoteByCategory(category: String): DataSource.Factory<Int, ArticleModelRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ArticleModelRoom>)
}