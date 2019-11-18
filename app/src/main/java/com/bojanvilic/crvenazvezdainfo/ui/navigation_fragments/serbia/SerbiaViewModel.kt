package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.serbia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.repository.IRepository
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.util.Category

class SerbiaViewModel(private val repository : IRepository) : ViewModel(), IViewContract.ViewModel {

    init {
        repository.updateArticlesInfo(Category.SERBIA)
    }

    fun getArticles() : LiveData<List<Model.Article>> {
        return repository.getArticlesFromNetwork()
    }
}