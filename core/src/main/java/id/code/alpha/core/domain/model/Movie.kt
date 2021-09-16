package id.code.alpha.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdropPath")
    val backdropPath: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("posterPath")
    val posterPath: String? = null,
    @SerializedName("releaseDate")
    val releaseDate: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("voteAverage")
    val voteAverage: Double? = null,
    @SerializedName("voteCount")
    val voteCount: Double? = null,
    @SerializedName("movieType")
    val movieType: String? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("isFavorite")
    val isFavorite: Boolean = false
) : Parcelable