package com.bojanvilic.crvenazvezdainfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.repository.IRepository
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract

class ArticleViewModel(private val repository : IRepository) : ViewModel(), IViewContract.ViewModel {

    init {
        repository.updateImages()
        repository.updateArticlesInfo()
    }

    fun getArticles() : LiveData<List<Model.Article>> {
        return repository.getArticlesFromNetwork()
    }

    fun getImage() : MediatorLiveData<List<Model.ImageModel>> {
        return repository.getImagesFromNetwork()
    }
}