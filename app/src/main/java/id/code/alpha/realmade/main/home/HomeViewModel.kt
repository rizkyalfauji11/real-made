package id.code.alpha.realmade.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.code.alpha.core.domain.usecase.MovieUseCase


open class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getMovie(type: String, page: Int? = 0) = movieUseCase.getAllMovies(type, page).asLiveData()
}