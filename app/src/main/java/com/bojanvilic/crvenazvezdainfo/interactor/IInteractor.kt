package com.bojanvilic.crvenazvezdainfo.interactor

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.util.Category

interface IInteractor {

    fun synchronizeArticles(category: Category)

    fun getArticles() : LiveData<PagedList<ArticleModelRoom>>

    fun getArticlesByCategory(category: String) : LiveData<PagedList<ArticleModelRoom>>

    fun getArticleId(id : Int) : ArticleModelRoom
}