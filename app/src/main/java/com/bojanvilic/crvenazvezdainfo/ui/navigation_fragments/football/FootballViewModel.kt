package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.football

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.repository.IRepository
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.util.Category

class FootballViewModel(private val repository : IRepository) : ViewModel(), IViewContract.ViewModel {

    init {
        repository.updateArticlesInfo(Category.FOOTBALL)
    }

    fun getArticles() : LiveData<List<Model.Article>> {
        return repository.getArticlesFromNetwork()
    }
}