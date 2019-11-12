package com.bojanvilic.crvenazvezdainfo.koin

import com.bojanvilic.crvenazvezdainfo.repository.IRepository
import com.bojanvilic.crvenazvezdainfo.repository.RepositoryImpl
import com.bojanvilic.crvenazvezdainfo.viewmodel.ArticleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RepositoryImpl as IRepository }

    viewModel { ArticleViewModel(get()) }

}