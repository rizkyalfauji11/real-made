package id.code.alpha.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdropPath")
    val backdropPath: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("posterPath")
    val posterPath: String?,
    @SerializedName("releaseDate")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("voteAverage")
    val voteAverage: Double?,
    @SerializedName("voteCount")
    val voteCount: Double?,
    @SerializedName("movieType")
    val movieType: String?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean
) : Parcelable