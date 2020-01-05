package com.bojanvilic.crvenazvezdainfo.interactor

import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.util.Category

interface IInteractor {

    fun synchronizeArticles(category: Category)

    fun getArticles() : LiveData<List<ArticleModelRoom>>

    fun getArticlesByCategory(category: String) : LiveData<List<ArticleModelRoom>>

    fun getArticleId(id : Int) : ArticleModelRoom
}