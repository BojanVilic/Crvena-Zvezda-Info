package com.bojanvilic.crvenazvezdainfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract

class ArticleViewModel(private var articlesList: LiveData<List<Model.Article>> = MutableLiveData()) : ViewModel(), IViewContract.ViewModel {

    override fun setArticles(article: LiveData<List<Model.Article>>) {
        articlesList = article
    }

    fun getArticles() : LiveData<List<Model.Article>> {
        return articlesList
    }
}