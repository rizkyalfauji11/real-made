package id.code.alpha.core.domain.repository

import id.code.alpha.core.data.Resource
import id.code.alpha.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryImpl {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean)
}