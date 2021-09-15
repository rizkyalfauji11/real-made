package id.code.alpha.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.code.alpha.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 3, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao
}