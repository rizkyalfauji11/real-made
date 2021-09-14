package id.code.alpha.core.data.source.remote.network

import id.code.alpha.core.data.source.remote.response.ListMovieResponse
import id.code.alpha.core.data.source.remote.response.popular.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{menu}")
    suspend fun getMovieList(@Path("menu", encoded = true) menu: String, @Query("api_key") apiKey: String?): PopularMoviesResponse
}