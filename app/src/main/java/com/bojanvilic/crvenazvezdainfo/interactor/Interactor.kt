package com.bojanvilic.crvenazvezdainfo.interactor

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import com.bojanvilic.crvenazvezdainfo.repositories.local_repository.ILocalRepository
import com.bojanvilic.crvenazvezdainfo.repositories.remote_repository.IRepository
import com.bojanvilic.crvenazvezdainfo.util.Category
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class Interactor(val remote: IRepository, val cache: ILocalRepository) : IInteractor {

    lateinit var flowable: Flowable<List<Model.Article>>
    private var newModelRoomList : MutableList<ArticleModelRoom> = arrayListOf()

    @SuppressLint("CheckResult")
    override fun synchronizeArticles(category: Category) {
        flowable = remote.updateArticlesInfo(category)
        flowable.toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onSuccess, this::onError)
    }

    private fun onSuccess(articlesList: List<Model.Article>) {
        articlesList.forEach {
            try {
                newModelRoomList.add(ArticleModelRoom(it.id, it.date, it.title.title, it.content.article_text, it._embedded.wpFeaturedmedia[0].source_url, it.categories[0].toString()))
            } catch (e : Exception){
                e.printStackTrace()
            }
        }
        cache.updateAll(newModelRoomList)
    }

    private fun onError(e: Throwable?) {
        if (e != null) {
            Log.e("Error", e.message!!)
        }
    }

    override fun getArticles(): LiveData<PagedList<ArticleModelRoom>> {
        return cache.getArticles()
    }

    override fun getRecommenedArticles(): LiveData<List<ArticleModelRoom>> {
        return cache.getRecommendedArticles()
    }


    override fun getArticlesByCategory(category: String): LiveData<PagedList<ArticleModelRoom>> {
        return cache.getArticleByCategory(category)
    }

    override fun getArticleId(id: Int): ArticleModelRoom {
        return cache.getArticleId(id)
    }
}