package id.code.alpha.core.data.source.local

import id.code.alpha.core.data.source.local.entity.MovieEntity
import id.code.alpha.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()
    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)
    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()
    fun setFavoriteMovie(movieEntity: MovieEntity, newStatus: Boolean) {
        movieEntity.isFavorite = newStatus
        movieDao.updateFavoriteMovie(movieEntity)
    }
}