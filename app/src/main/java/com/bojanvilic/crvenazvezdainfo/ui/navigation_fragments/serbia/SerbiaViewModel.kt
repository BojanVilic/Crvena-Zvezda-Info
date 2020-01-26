package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.serbia

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.interactor.Interactor
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.util.Category

class SerbiaViewModel(private val interactor: Interactor) : ViewModel(), IViewContract.ViewModel {

    var connectivityAvailable : Boolean = false

    init {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.SERBIA)
        }
    }

    override fun getOnlineArticles() : LiveData<PagedList<ArticleModelRoom>> {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.SERBIA)
        }
        return interactor.getArticlesByCategory("4")
    }
}