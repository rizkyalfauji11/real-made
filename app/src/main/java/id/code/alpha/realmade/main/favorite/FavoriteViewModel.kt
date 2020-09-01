package id.code.alpha.realmade.main.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.code.alpha.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}