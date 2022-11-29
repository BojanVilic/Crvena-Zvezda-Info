package com.bojanvilic.crvenazvezdainfo.data.persistence

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

    @Query("SELECT * FROM articles_table WHERE id != :currentArticleId ORDER BY date DESC Limit 6")
    fun getRecommendedArticles(currentArticleId: String) : Flow<List<ArticleModelRoom>>

    @Query("SELECT * FROM articles_table ORDER BY date DESC")
    fun getAllArticlesPaged() : Flow<List<ArticleModelRoom>>

    @Query("SELECT * FROM articles_table WHERE id = :id")
    fun getArticleById(id: String): Flow<ArticleModelRoom>

    @Query("SELECT * FROM articles_table WHERE category = :category ORDER BY date DESC")
    fun getArticlesByCategory(category: String): Flow<List<ArticleModelRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ArticleModelRoom>)
}