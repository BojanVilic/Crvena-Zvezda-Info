package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model

interface IViewContract {

    interface View {

    }

    interface ViewModel {
        fun setArticles(article: LiveData<List<Model.Article>>)

    }

    interface Presenter {
        fun bind()
    }
}