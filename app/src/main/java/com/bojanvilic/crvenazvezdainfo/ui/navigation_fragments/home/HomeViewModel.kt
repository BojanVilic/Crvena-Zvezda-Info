package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.interactor.Interactor
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.util.Category

class HomeViewModel(private val interactor: Interactor) : ViewModel(), IViewContract.ViewModel {

    var connectivityAvailable : Boolean = false

    init {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.HOME)
        }
    }

    override fun getOnlineArticles() : LiveData<PagedList<ArticleModelRoom>> {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.HOME)
        }
        return interactor.getArticles()
    }

    fun getRecommendedArticles(): LiveData<List<ArticleModelRoom>> {
        return interactor.getRecommenedArticles()
    }


}