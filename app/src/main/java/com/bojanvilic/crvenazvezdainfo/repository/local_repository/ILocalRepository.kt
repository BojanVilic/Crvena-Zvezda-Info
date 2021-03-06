package com.bojanvilic.crvenazvezdainfo.repository.local_repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom

interface ILocalRepository {
    fun getArticles() : LiveData<PagedList<ArticleModelRoom>>

    fun getArticleId(id: Int) : ArticleModelRoom

    fun getRecommendedArticles() : LiveData<List<ArticleModelRoom>>

    fun getArticleByCategory(category: String) : LiveData<PagedList<ArticleModelRoom>>

    fun updateAll(list: List<ArticleModelRoom>)
}