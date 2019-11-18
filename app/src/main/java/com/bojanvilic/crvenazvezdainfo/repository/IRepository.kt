package com.bojanvilic.crvenazvezdainfo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.util.Category

interface IRepository {

    fun updateArticlesInfo(category: Category)
    fun getArticlesFromNetwork(): LiveData<List<Model.Article>>
}