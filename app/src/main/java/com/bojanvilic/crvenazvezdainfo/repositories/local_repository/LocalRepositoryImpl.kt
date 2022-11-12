package com.bojanvilic.crvenazvezdainfo.repositories.local_repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalRepositoryImpl(private val articleDao: ArticleDao) : ILocalRepository {

    override fun getArticles(): LiveData<PagedList<ArticleModelRoom>> {
        return articleDao.getAllArticlesPaged().toLiveData(Config(pageSize = 35, enablePlaceholders = true, maxSize = 200))
    }

    override fun getArticleId(id: Int): ArticleModelRoom {
        return articleDao.getNoteById(id)
    }

    override fun getRecommendedArticles(): LiveData<List<ArticleModelRoom>> {
        return articleDao.getRecommendedArticles()
    }

    override fun getArticleByCategory(category: String): LiveData<PagedList<ArticleModelRoom>> {
        return articleDao.getNoteByCategory(category).toLiveData(Config(pageSize = 35, enablePlaceholders = true, maxSize = 200))
    }

    @SuppressLint("CheckResult")
    override fun updateAll(list: List<ArticleModelRoom>) {
        articleDao.insertAll(list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("RxJava", "Insert Success") },
                { Log.d("RxJava", "Insert Error") }
            )
    }
}