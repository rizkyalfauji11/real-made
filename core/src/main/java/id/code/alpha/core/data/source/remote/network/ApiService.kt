package id.code.alpha.core.data.source.remote.network

import id.code.alpha.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("list/1")
    suspend fun getMovieList(@Query("api_key") apiKey: String?): ListMovieResponse
}