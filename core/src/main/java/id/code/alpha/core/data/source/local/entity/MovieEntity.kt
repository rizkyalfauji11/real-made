package id.code.alpha.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tb_movie")
data class MovieEntity(
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double? = null,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity: Double? = null,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    var voteCount: Double? = null,

    @SerializedName("movie_type")
    @ColumnInfo(name = "movie_type")
    var movieType: String? = null,

    @SerializedName("page")
    @ColumnInfo(name = "page")
    var page: Int? = 1,

    @SerializedName("isFavorite")
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
)