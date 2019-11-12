package com.bojanvilic.crvenazvezdainfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.repository.IRepository
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract

class ArticleViewModel(private val repository : IRepository) : ViewModel(), IViewContract.ViewModel {

    fun getArticles() : LiveData<List<Model.Article>> {
        return repository.getArticlesFromNetwork()
    }

    fun getImage() : LiveData<Model.ImageModel> {
        return repository.getImagesFromNetwork()
    }
}