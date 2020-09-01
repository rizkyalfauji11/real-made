package id.code.alpha.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Double?
)