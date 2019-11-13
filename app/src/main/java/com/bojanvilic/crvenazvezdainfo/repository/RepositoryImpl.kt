package com.bojanvilic.crvenazvezdainfo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.bojanvilic.crvenazvezdainfo.data.api.IApiService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RepositoryImpl : IRepository {

    var imageList : MediatorLiveData<List<Model.ImageModel>> = MediatorLiveData()
    var listOfImages : MutableList<Model.ImageModel> = ArrayList()
    lateinit  var listOfArticles : LiveData<List<Model.Article>>

    private val apiService by lazy {
        IApiService.create()
    }

    override fun getImagesFromNetwork(): MediatorLiveData<List<Model.ImageModel>> {
        return imageList
    }

    override fun getArticlesFromNetwork() : LiveData<List<Model.Article>>{
        return listOfArticles
    }

    override fun updateArticlesInfo() {
        listOfArticles = LiveDataReactiveStreams.fromPublisher(apiService.getArticlesList().subscribeOn(Schedulers.io()))
    }

    private fun getListOfArticlesClean() : Flowable<List<Model.Article>> {
        return apiService.getArticlesList()
    }

    private fun getListOfImages(model : Model.Article) : Flowable<Model.ImageModel> {
        return apiService.getImage(model.featured_media)
    }

    override fun updateImages() {
        getListOfArticlesClean()
            .flatMapIterable { it }
            .flatMap { getListOfImages(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                listOfImages.add(result)
                imageList.postValue(listOfImages)
            }
    }
}