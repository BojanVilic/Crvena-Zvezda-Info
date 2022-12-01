package com.bojanvilic.crvenazvezdainfo.data.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles_table")
data class ArticleModelRoom(

    @PrimaryKey
    val id: Int? = 0,
    val date: String? = "",
    val title: String? = "",
    val content: String? = "",
    val imageUrl: String? = "",
    val category: String? = "",
    val excerpt: String? = "",
    val link: String? = ""
) : Serializable