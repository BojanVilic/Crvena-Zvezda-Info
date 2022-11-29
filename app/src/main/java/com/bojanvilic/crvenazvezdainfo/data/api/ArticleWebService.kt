package com.bojanvilic.crvenazvezdainfo.data.api

import com.bojanvilic.crvenazvezdainfo.data.datamodel.ArticleEntity
import com.bojanvilic.crvenazvezdainfo.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ArticleWebService {

    @GET("posts?_embed=true&per_page=40")
    suspend fun getMostRecentArticlesList(): List<ArticleEntity>

    @GET("posts?_embed=true&per_page=40")
    suspend fun getArticlesListByCategory(@Query("categories") category: Int): List<ArticleEntity>

    @GET("posts/{id}?_embed=true")
    suspend fun getArticleDetails(@Path("id") id: String): ArticleEntity

    companion object {
        fun create(): ArticleWebService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ArticleWebService::class.java)
        }
    }
}