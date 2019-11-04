package com.bojanvilic.crvenazvezdainfo.repository

import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import io.reactivex.Observable

interface BaseUseCase {

    fun execute() : LiveData<List<Model.Article>>
}