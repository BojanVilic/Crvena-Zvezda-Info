package com.bojanvilic.crvenazvezdainfo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import com.bojanvilic.crvenazvezdainfo.data.api.IApiService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RepositoryImpl : IRepository {

    private lateinit var mLivedata : MediatorLiveData<List<Model.Article>>

    private val apiService by lazy {
        IApiService.create()
    }

    override fun getArticlesFromNetwork() : LiveData<List<Model.Article>>{
        return getListOfArticles()
    }

    private fun getListOfArticles() : LiveData<List<Model.Article>> {
        return LiveDataReactiveStreams.fromPublisher(apiService.getArticlesList().subscribeOn(Schedulers.io()))
    }

    private fun getListOfImages(model : Model.Article) : Observable<Model.ImageModel> {
        return apiService.getImage(model.featured_media)
    }

//    private fun packImagesIntoList() : MutableList<Model.ImageModel>{
//        lateinit var listOfImages : MutableList<Model.ImageModel>
//        getListOfArticles()
//            .flatMapIterable { it }
//            .flatMap { getListOfImages(it) }
//            .toList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//            }
//        return listOfImages
//    }
//
//    private fun getArticlesIntoList(){
//        lateinit var listOfArticles : List<Model.Article>
//        getListOfArticles()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { result ->
//                Log.d("TAG", result[0].title.title)
//                listOfArticles = result
//            }
//    }
}