package id.code.alpha.core.domain.usecase

import id.code.alpha.core.data.Resource
import id.code.alpha.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovies(menu: String): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean)
}