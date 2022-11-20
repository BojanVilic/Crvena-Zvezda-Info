package com.bojanvilic.crvenazvezdainfo.repositories.local_repository

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleDao
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom

class LocalRepositoryImpl(private val articleDao: ArticleDao) : ILocalRepository {


    override fun getArticleByCategory(category: String): LiveData<PagedList<ArticleModelRoom>> {
        return articleDao.getNoteByCategory(category).toLiveData(Config(pageSize = 35, enablePlaceholders = true, maxSize = 200))
    }

//    @SuppressLint("CheckResult")
//    override fun updateAll(list: List<ArticleModelRoom>) {
//        articleDao.insertAll(list)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { Log.d("RxJava", "Insert Success") },
//                { Log.d("RxJava", "Insert Error") }
//            )
//    }
}