package com.bojanvilic.crvenazvezdainfo.repository

import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model

interface IRepository {

    fun getArticlesFromNetwork(): LiveData<List<Model.Article>>
    fun getImagesFromNetwork() : LiveData<Model.ImageModel>
}