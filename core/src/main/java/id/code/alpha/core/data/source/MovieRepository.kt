package id.code.alpha.core.data.source

import id.code.alpha.core.data.Resource
import id.code.alpha.core.data.source.local.LocalDataSource
import id.code.alpha.core.data.source.remote.RemoteDataSource
import id.code.alpha.core.data.source.remote.network.ApiResponse
import id.code.alpha.core.data.source.remote.response.popular.PopularMovie
import id.code.alpha.core.domain.model.Movie
import id.code.alpha.core.domain.repository.MovieRepositoryImpl
import id.code.alpha.core.utils.AppExecutors
import id.code.alpha.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieRepositoryImpl {
    override fun getAllMovies(menu: String, page: Int?): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<PopularMovie>>(appExecutors) {
            override suspend fun createCall(): Flow<ApiResponse<List<PopularMovie>>> =
                remoteDataSource.getAllMovies(menu, page)

            override fun shouldFetchData(it: List<Movie>?): Boolean = it == null || it.isEmpty()

            override fun loadFromDatabase(): Flow<List<Movie>> =
                localDataSource.getAllMovies(menu, page).map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override suspend fun saveCallResult(data: List<PopularMovie>) {
                val movieList = DataMapper.mapResponsesToEntities(data, menu, page)
                localDataSource.insertMovies(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(
                it
            )
        }

    override fun setFavoriteMovie(movie: Movie, newStatus: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movieEntity, newStatus)
        }
    }

}