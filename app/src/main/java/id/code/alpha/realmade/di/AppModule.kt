package id.code.alpha.realmade.di

import id.code.alpha.core.domain.usecase.MovieInteractor
import id.code.alpha.core.domain.usecase.MovieUseCase
import id.code.alpha.realmade.detail.DetailViewModel
import id.code.alpha.realmade.main.favorite.FavoriteViewModel
import id.code.alpha.realmade.main.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}