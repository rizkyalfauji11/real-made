package id.code.alpha.core.data.source.remote

import id.code.alpha.core.BuildConfig
import id.code.alpha.core.data.source.remote.network.ApiResponse
import id.code.alpha.core.data.source.remote.network.ApiService
import id.code.alpha.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> =
        flow {
            try {
                val response = apiService.getMovieList(BuildConfig.API_KEY)
                val dataArray = response.list
                if (dataArray != null)
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(dataArray))
                    } else {
                        emit(ApiResponse.Empty)
                    }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                e.printStackTrace()
            }
        }.flowOn(Dispatchers.IO)
}