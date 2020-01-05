package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom

interface IViewContract {

    interface View {
        //fun setScreenInfo()
    }

    interface ViewModel {
        fun getOnlineArticles() : LiveData<List<ArticleModelRoom>>
    }

    interface Presenter {
        fun bind()
    }
}