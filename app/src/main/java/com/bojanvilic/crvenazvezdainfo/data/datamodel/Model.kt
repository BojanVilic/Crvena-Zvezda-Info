package com.bojanvilic.crvenazvezdainfo.data.datamodel

import com.google.gson.annotations.SerializedName

object Model {
    data class Article(
        val id: Int,
        val date: String,
        val title: Title,
        val content: Content,
        val _embedded: Embedded
    )

    data class Title (
        @SerializedName("rendered")
        val title: String
    )

    data class Content (
        @SerializedName("rendered")
        val article_text: String
    )

    data class Embedded(
        @SerializedName("wp:featuredmedia")
        val wpFeaturedmedia: List<FeatureMediaItem>
    )

    data class FeatureMediaItem(
        val source_url: String
    )
}