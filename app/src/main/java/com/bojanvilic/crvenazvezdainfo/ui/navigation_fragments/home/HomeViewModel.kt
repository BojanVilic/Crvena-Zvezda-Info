package com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.repositories.ArticleRepository
import com.bojanvilic.crvenazvezdainfo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    suspend fun test(): Flow<Resource<List<ArticleModelRoom>>> {
        return articleRepository.getLatestArticles()
    }

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
            articleRepository.getLatestArticles().collect {
                it.data?.let {
                    Timber.d("PROBA: ${it[0].title}")
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