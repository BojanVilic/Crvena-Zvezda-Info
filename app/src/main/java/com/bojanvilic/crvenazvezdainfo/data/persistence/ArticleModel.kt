package com.bojanvilic.crvenazvezdainfo.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_table")
data class ArticleModel(

    @PrimaryKey
    val id: Int,
    val date: String,
    val title: String,
    val content: String,
    val imageUri: String,
    val category: String
    )