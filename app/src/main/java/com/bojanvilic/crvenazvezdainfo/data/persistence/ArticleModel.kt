package com.bojanvilic.crvenazvezdainfo.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleModel(

    @PrimaryKey
    val id: Int,
    val date: String,
    val title: String,
    val content: String,
    val imageUri: String
    )