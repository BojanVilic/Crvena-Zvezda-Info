package com.bojanvilic.crvenazvezdainfo.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import com.bojanvilic.crvenazvezdainfo.data.datamodel.Model
import com.bojanvilic.crvenazvezdainfo.repository.usecase.NetworkArticlesRequest
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract


class ArticlePresenter (private var view: IViewContract.View,
                        private var viewModel: IViewContract.ViewModel,
                        private var networkArticlesRequest : NetworkArticlesRequest) : IViewContract.Presenter {
    

    override fun bind() {
        viewModel.setArticles(networkArticlesRequest.execute())
    }
}