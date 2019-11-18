package com.bojanvilic.crvenazvezdainfo.koin

import com.bojanvilic.crvenazvezdainfo.repository.IRepository
import com.bojanvilic.crvenazvezdainfo.repository.RepositoryImpl
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.basketball.BasketballViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.football.FootballViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomeViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.other.OtherViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.serbia.SerbiaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { RepositoryImpl as IRepository }

    viewModel { HomeViewModel(get()) }

    viewModel { FootballViewModel(get()) }

    viewModel { BasketballViewModel(get()) }

    viewModel { OtherViewModel(get()) }

    viewModel { SerbiaViewModel(get()) }

}