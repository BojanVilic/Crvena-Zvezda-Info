package com.bojanvilic.crvenazvezdainfo.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model

@Dao
interface ArticleDao {

    @Insert
    fun insert(article: ArticleModel)

    @Query("SELECT * FROM articles_table")
    fun getAllArticles() : LiveData<List<Model.Article>>
}