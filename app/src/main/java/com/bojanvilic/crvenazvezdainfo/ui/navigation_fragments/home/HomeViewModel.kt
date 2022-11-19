package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home

import androidx.lifecycle.ViewModel
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

//    var connectivityAvailable : Boolean = false
//
//    init {
//        if (connectivityAvailable) {
//            interactor.synchronizeArticles(Category.HOME)
//        }
//    }

//    override fun getOnlineArticles() : LiveData<PagedList<ArticleModelRoom>> {
//        if (connectivityAvailable) {
//            interactor.synchronizeArticles(Category.HOME)
//        }
//        return interactor.getArticles()
//    }

//    fun getRecommendedArticles(): LiveData<List<ArticleModelRoom>> {
//        return interactor.getRecommenedArticles()
//    }

}