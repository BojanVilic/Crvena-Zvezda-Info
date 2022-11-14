package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
        }
    }

    fun init() {
        viewModelScope.launch {
//            articleRepository.getLatestArticles().map { resource ->
//                resource.data?.let {
//                    Timber.d("PROBA: ${it[0].title}")
//                }
//            }
            
            articleRepository.getLatestArticles()
            articleRepository.getLatestArticles().collect { resource ->
                resource.data?.let {
                    if (it.isNotEmpty()) {
                        Timber.d("PROBA: ${it[0].title}")
                    }
                }
            }
        }
    }

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