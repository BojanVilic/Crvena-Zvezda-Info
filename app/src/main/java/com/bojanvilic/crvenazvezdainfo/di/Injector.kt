package com.bojanvilic.crvenazvezdainfo.di

import androidx.appcompat.app.AppCompatActivity
import com.bojanvilic.crvenazvezdainfo.presenter.ArticlePresenter
import com.bojanvilic.crvenazvezdainfo.repository.RepositoryImpl
import com.bojanvilic.crvenazvezdainfo.ui.IViewContract
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomePageFragment

class Injector(private var activity: AppCompatActivity) {

    fun providePresenter(view: HomePageFragment): IViewContract.Presenter {
        return ArticlePresenter(view)
    }
}