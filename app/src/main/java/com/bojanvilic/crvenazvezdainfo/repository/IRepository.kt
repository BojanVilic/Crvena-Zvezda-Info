package com.bojanvilic.crvenazvezdainfo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model

interface IRepository {

    fun updateArticlesInfo()
    fun updateImages()
    fun getArticlesFromNetwork(): LiveData<List<Model.Article>>
    fun getImagesFromNetwork() : MediatorLiveData<List<Model.ImageModel>>
}