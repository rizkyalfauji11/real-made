package id.code.alpha.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("items")
    val list: List<MovieResponse>?
)