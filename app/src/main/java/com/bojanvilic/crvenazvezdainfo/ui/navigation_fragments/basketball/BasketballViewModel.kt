package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.basketball

import androidx.lifecycle.*
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.interactor.Interactor
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.util.Category

class BasketballViewModel(private val interactor: Interactor) : ViewModel(), IViewContract.ViewModel {

    var connectivityAvailable : Boolean = false

    init {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.BASKETBALL)
        }
    }

    override fun getOnlineArticles() : LiveData<List<ArticleModelRoom>> {
        if (connectivityAvailable) {
            interactor.synchronizeArticles(Category.BASKETBALL)
        }
        return interactor.getArticlesByCategory("5")
    }

}