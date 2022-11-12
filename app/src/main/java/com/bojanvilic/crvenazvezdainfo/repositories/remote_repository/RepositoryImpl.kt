package com.bojanvilic.crvenazvezdainfo.repositories.remote_repository

import com.bojanvilic.crvenazvezdainfo.data.api.ArticleWebService
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.util.Category
import io.reactivex.Flowable

object RepositoryImpl : IRepository {

    private var newModelRoomList : MutableList<ArticleModelRoom> = arrayListOf()
    private lateinit var flowable : Flowable<List<Model.Article>>

    private val apiService by lazy {
        ArticleWebService.create()
    }

    override fun getArticlesFromNetwork() : MutableList<ArticleModelRoom> {
        return newModelRoomList
    }

    override fun updateArticlesInfo(category: Category): Flowable<List<Model.Article>> {
        flowable = when(category){
            Category.HOME -> apiService.getArticlesList()
            Category.FOOTBALL -> apiService.getFootballArticlesList()
            Category.BASKETBALL -> apiService.getBasketballArticlesList()
            Category.OTHER -> apiService.getOtherArticlesList()
            Category.SERBIA -> apiService.getSerbiaArticlesList()
        }

        return flowable
    }


}