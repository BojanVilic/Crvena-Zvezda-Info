package com.bojanvilic.crvenazvezdainfo.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bojanvilic.crvenazvezdainfo.presenter.ArticlePresenter
import com.bojanvilic.crvenazvezdainfo.repository.RepositoryImpl
import com.bojanvilic.crvenazvezdainfo.repository.usecase.NetworkArticlesRequest
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomePageFragment
import com.bojanvilic.crvenazvezdainfo.viewmodel.ArticleViewModel

class Injector(private var activity: AppCompatActivity) {

    private var repository : RepositoryImpl = RepositoryImpl

    fun providePresenter(view: HomePageFragment): IViewContract.Presenter {
        return ArticlePresenter(
            view,
            ViewModelProviders.of(activity).get(ArticleViewModel::class.java),
            NetworkArticlesRequest(repository))
    }
}