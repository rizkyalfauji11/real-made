package id.code.alpha.core.utils

import id.code.alpha.core.data.source.local.entity.MovieEntity
import id.code.alpha.core.data.source.remote.response.popular.PopularMovie
import id.code.alpha.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<PopularMovie>, type: String?, page: Int?): List<MovieEntity> =
        input.map {
            MovieEntity(
                id = it.id?: 0,
                title = it.title,
                overview = it.overview,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount?: 0.0,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                page = page,
                movieType = type
            )
        }

    fun mapEntitiesToDomain(input: List<MovieEntity>) =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                movieType = it.movieType,
                page = it.page,
                isFavorite = it.isFavorite ?: false
            )
        }

    fun mapDomainToEntity(it: Movie) =
        MovieEntity(
            id = it.id,
            title = it.title,
            overview = it.overview,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            backdropPath = it.backdropPath,
            popularity = it.popularity,
            posterPath = it.posterPath,
            movieType = it.movieType,
            releaseDate = it.releaseDate
        )
}