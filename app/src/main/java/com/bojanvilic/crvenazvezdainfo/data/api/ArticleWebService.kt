package com.bojanvilic.crvenazvezdainfo.data.api

import com.bojanvilic.crvenazvezdainfo.data.datamodel.ArticleEntity
import com.bojanvilic.crvenazvezdainfo.util.BASE_URL
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ArticleWebService {

    @GET("posts?_embed=true&per_page=40")
    suspend fun getArticlesList(): List<ArticleEntity>

    @GET("posts/{id}?_embed=true")
    suspend fun getArticleDetails(@Path("id") id: String): ArticleEntity

    @GET("posts?_embed=true&per_page=63&categories=1")
    fun getFootballArticlesList() : Flowable<List<ArticleEntity>>

    @GET("posts?_embed=true&per_page=63&categories=3")
    fun getOtherArticlesList() : Flowable<List<ArticleEntity>>

    @GET("posts?_embed=true&per_page=63&categories=4")
    fun getSerbiaArticlesList() : Flowable<List<ArticleEntity>>

    @GET("posts?_embed=true&per_page=63&categories=5")
    fun getBasketballArticlesList() : Flowable<List<ArticleEntity>>

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