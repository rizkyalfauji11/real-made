package id.code.alpha.realmade.detail

import androidx.lifecycle.ViewModel
import id.code.alpha.core.domain.model.Movie
import id.code.alpha.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel(){
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}