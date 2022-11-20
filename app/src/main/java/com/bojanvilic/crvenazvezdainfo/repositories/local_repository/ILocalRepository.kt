package com.bojanvilic.crvenazvezdainfo.repositories.local_repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom

interface ILocalRepository {

    fun getArticleByCategory(category: String) : LiveData<PagedList<ArticleModelRoom>>

//    fun updateAll(list: List<ArticleModelRoom>)
}