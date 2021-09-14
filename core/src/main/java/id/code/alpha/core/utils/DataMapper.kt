package id.code.alpha.core.utils

import id.code.alpha.core.data.source.local.entity.MovieEntity
import id.code.alpha.core.data.source.remote.response.MovieResponse
import id.code.alpha.core.data.source.remote.response.popular.PopularMovie
import id.code.alpha.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<PopularMovie>): List<MovieEntity> =
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
                releaseDate = it.releaseDate
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
            releaseDate = it.releaseDate
        )
}