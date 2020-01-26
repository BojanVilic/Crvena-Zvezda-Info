package com.bojanvilic.crvenazvezdainfo.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bojanvilic.crvenazvezdainfo.data.persistence.ArticleModelRoom

interface IViewContract {

    interface View {
        //fun setScreenInfo()
    }

    interface ViewModel {
        fun getOnlineArticles() : LiveData<PagedList<ArticleModelRoom>>
    }

    interface Presenter {
        fun bind()
    }
}