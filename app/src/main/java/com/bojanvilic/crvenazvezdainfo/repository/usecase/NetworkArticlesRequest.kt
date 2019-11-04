package com.bojanvilic.crvenazvezdainfo.repository.usecase

import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.repository.BaseUseCase
import com.bojanvilic.crvenazvezdainfo.repository.IRepository

class NetworkArticlesRequest (private val repository : IRepository) : BaseUseCase {

    override fun execute() : LiveData<List<Model.Article>> {
        return repository.getArticlesFromNetwork()
    }
}