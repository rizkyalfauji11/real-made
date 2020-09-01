package id.code.alpha.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("status_message")
    val message: String?,
    @SerializedName("status_code")
    val statusCode: String?,
    @SerializedName("items")
    val list: List<MovieResponse>?
)