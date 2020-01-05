package com.bojanvilic.crvenazvezdainfo.repository.local_repository

import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom

interface ILocalRepository {
    fun getArticles() : LiveData<List<ArticleModelRoom>>

    fun getArticleId(id: Int) : ArticleModelRoom

    fun getArticleByCategory(category: String) : LiveData<List<ArticleModelRoom>>

    fun updateAll(list: List<ArticleModelRoom>)
}