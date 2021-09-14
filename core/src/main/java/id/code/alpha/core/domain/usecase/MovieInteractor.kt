package id.code.alpha.core.domain.usecase

import id.code.alpha.core.data.Resource
import id.code.alpha.core.domain.model.Movie
import id.code.alpha.core.domain.repository.MovieRepositoryImpl
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: MovieRepositoryImpl) : MovieUseCase {
    override fun getAllMovies(menu: String): Flow<Resource<List<Movie>>> =
        movieRepository.getAllMovies(menu)

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieRepository.setFavoriteMovie(movie, newStatus)

}