package com.bojanvilic.crvenazvezdainfo.data.datamodel

import com.google.gson.annotations.SerializedName

object Model {
    data class Article(
        val id: Int,
        val date: String,
        val title: Title,
        val content: Content,
        val featured_media: Int
    )

    data class Title (
        @SerializedName("rendered")
        val title: String
    )

    data class Content (
        @SerializedName("rendered")
        val article_text: String
    )

    data class ImageModel(
        val guid: Guid
    )

    data class Guid(
        @SerializedName("rendered")
        val imageUrl: String
    )
}