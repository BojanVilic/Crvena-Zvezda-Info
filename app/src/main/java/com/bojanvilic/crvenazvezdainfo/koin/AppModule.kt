package com.bojanvilic.crvenazvezdainfo.koin

import androidx.room.Room
import com.bojanvilic.crvenazvezdainfo.data.persistence.OfflineDatabase
import com.bojanvilic.crvenazvezdainfo.interactor.IInteractor
import com.bojanvilic.crvenazvezdainfo.interactor.Interactor
import com.bojanvilic.crvenazvezdainfo.repository.local_repository.ILocalRepository
import com.bojanvilic.crvenazvezdainfo.repository.remote_repository.IRepository
import com.bojanvilic.crvenazvezdainfo.repository.local_repository.LocalRepositoryImpl
import com.bojanvilic.crvenazvezdainfo.repository.remote_repository.RepositoryImpl
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.basketball.BasketballViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.football.FootballViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.home.HomeViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.other.OtherViewModel
import com.bojanvilic.crvenazvezdainfo.ui.navigation_fragments.serbia.SerbiaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val OFFLINE_DATABASE = "offline_articles"

val appModule = module {

    single { Room.databaseBuilder(get(), OfflineDatabase::class.java, OFFLINE_DATABASE).fallbackToDestructiveMigration().build() }

    single { get<OfflineDatabase>().roomArticleDao() }

    single<IRepository> {
        RepositoryImpl
    }

    single<ILocalRepository> {
        LocalRepositoryImpl(get())
    }

    single { Interactor(get(), get()) }

    viewModel { HomeViewModel(get()) }

    viewModel { FootballViewModel(get()) }

    viewModel { BasketballViewModel(get()) }

    viewModel { OtherViewModel(get()) }

    viewModel { SerbiaViewModel(get()) }

}