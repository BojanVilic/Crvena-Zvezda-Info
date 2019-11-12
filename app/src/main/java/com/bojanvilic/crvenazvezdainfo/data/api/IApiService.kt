package com.bojanvilic.crvenazvezdainfo.data.api

import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.util.BASE_URL
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface IApiService {

    @GET("wp-json/wp/v2/posts")
    fun getArticlesList() : Flowable<List<Model.Article>>


    @GET("wp-json/wp/v2/media/{image_id}")
    fun getImage(@Path("image_id") image_id: Int) : Flowable<Model.ImageModel>

    companion object {
        fun create(): IApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(IApiService::class.java)
        }
    }
}