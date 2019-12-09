package com.bojanvilic.crvenazvezdainfo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.bojanvilic.crvenazvezdainfo.data.api.IApiService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.util.Category
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RepositoryImpl : IRepository {

    private lateinit  var listOfArticles : LiveData<List<Model.Article>>

    private val apiService by lazy {
        IApiService.create()
    }

    override fun getArticlesFromNetwork() : LiveData<List<Model.Article>>{
        return listOfArticles
    }

    override fun updateArticlesInfo(category: Category) {
        listOfArticles = when(category){
            Category.HOME -> LiveDataReactiveStreams.fromPublisher(apiService.getArticlesList().subscribeOn(Schedulers.io()))
            Category.FOOTBALL -> LiveDataReactiveStreams.fromPublisher(apiService.getFootballArticlesList().subscribeOn(Schedulers.io()))
            Category.BASKETBALL -> LiveDataReactiveStreams.fromPublisher(apiService.getBasketballArticlesList().subscribeOn(Schedulers.io()))
            Category.OTHER -> LiveDataReactiveStreams.fromPublisher(apiService.getOtherArticlesList().subscribeOn(Schedulers.io()))
            Category.SERBIA -> LiveDataReactiveStreams.fromPublisher(apiService.getSerbiaArticlesList().subscribeOn(Schedulers.io()))
        }
    }
}