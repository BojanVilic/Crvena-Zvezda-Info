package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.other

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.interactor.Interactor
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.util.Category

class OtherViewModel(private val interactor: Interactor) : ViewModel(), IViewContract.ViewModel {

    var connectivityAvailable : Boolean = false

    init {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.OTHER)
        }
    }

    override fun getOnlineArticles() : LiveData<PagedList<ArticleModelRoom>> {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.OTHER)
        }
        return interactor.getArticlesByCategory("3")
    }
}