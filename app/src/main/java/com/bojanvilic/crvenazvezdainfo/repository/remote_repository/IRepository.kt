package com.bojanvilic.crvenazvezdainfo.repository.remote_repository

import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.util.Category
import io.reactivex.Flowable

interface IRepository {
    fun updateArticlesInfo(category: Category): Flowable<List<Model.Article>>
    fun getArticlesFromNetwork() : MutableList<ArticleModelRoom>
}