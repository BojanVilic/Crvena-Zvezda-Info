package com.bojanvilic.crvenazvezdainfo.data.datamodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArticleEntity(
    val id: Int,
    @SerializedName("date_gmt")
    val date: String,
    val title: Title,
    val content: Content,
    val _embedded: Embedded,
    val categories: List<Int>,
    val excerpt: Excerpt,
    val link: String
): Serializable

data class Title (
    @SerializedName("rendered")
    val title: String
) :Serializable

data class Content (
    @SerializedName("rendered")
    val article_text: String
) : Serializable

data class Embedded(
    @SerializedName("wp:featuredmedia")
    val wpFeaturedmedia: List<FeatureMediaItem>
) : Serializable

data class FeatureMediaItem(
    val source_url: String
) : Serializable

data class Excerpt(
    val rendered: String
)