package com.bojanvilic.crvenazvezdainfo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Transformations
import com.bojanvilic.crvenazvezdainfo.data.api.IApiService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RepositoryImpl : IRepository {

    private val apiService by lazy {
        IApiService.create()
    }

    override fun getImagesFromNetwork(): LiveData<Model.ImageModel> {
        return packImagesIntoList()
    }

    override fun getArticlesFromNetwork() : LiveData<List<Model.Article>>{
        return getListOfArticles()
    }

    private fun getListOfArticles() : LiveData<List<Model.Article>> {
        return LiveDataReactiveStreams.fromPublisher(apiService.getArticlesList().subscribeOn(Schedulers.io()))
    }

    private fun getListOfArticlesClean() : Flowable<List<Model.Article>> {
        return apiService.getArticlesList()
    }

    private fun getListOfImages(model : Model.Article) : Flowable<Model.ImageModel> {
        return apiService.getImage(model.featured_media)
    }

    private fun packImagesIntoList() : LiveData<Model.ImageModel>{
        lateinit var listOfImages : LiveData<Model.ImageModel>
        listOfImages = getListOfArticlesClean()
            .flatMapIterable { it }
            .flatMap { getListOfImages(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .to { LiveDataReactiveStreams.fromPublisher(it) }
        return listOfImages
    }
}