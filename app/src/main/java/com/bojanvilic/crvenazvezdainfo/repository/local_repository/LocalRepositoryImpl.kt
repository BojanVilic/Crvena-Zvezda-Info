package com.bojanvilic.crvenazvezdainfo.repository.local_repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalRepositoryImpl(private val articleDao: ArticleDao) : ILocalRepository {

    override fun getArticles(): LiveData<List<ArticleModelRoom>> {
        return articleDao.getAllArticles()
    }

    override fun getArticleId(id: Int): ArticleModelRoom {
        return articleDao.getNoteById(id)
    }

    override fun getArticleByCategory(category: String): LiveData<List<ArticleModelRoom>> {
        return articleDao.getNoteByCategory(category)
    }

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