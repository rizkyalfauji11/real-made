package id.code.alpha.core.data.source.local.room

import androidx.room.*
import id.code.alpha.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM tb_movie WHERE movie_type = :type AND page = :page")
    fun getAllMovies(type: String, page: Int?): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(tourism: List<MovieEntity>)

    @Query("SELECT * FROM tb_movie where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Update
    fun updateFavoriteMovie(tourism: MovieEntity)
}