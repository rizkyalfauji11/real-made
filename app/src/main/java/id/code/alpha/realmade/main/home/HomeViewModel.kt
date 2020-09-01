package id.code.alpha.realmade.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.code.alpha.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()
}