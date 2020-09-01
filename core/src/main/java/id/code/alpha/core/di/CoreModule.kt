package id.code.alpha.core.di

import androidx.room.Room
import id.code.alpha.core.BuildConfig
import id.code.alpha.core.data.source.MovieRepository
import id.code.alpha.core.data.source.local.LocalDataSource
import id.code.alpha.core.data.source.local.room.MovieDatabase
import id.code.alpha.core.data.source.remote.RemoteDataSource
import id.code.alpha.core.data.source.remote.network.ApiService
import id.code.alpha.core.domain.repository.MovieRepositoryImpl
import id.code.alpha.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().moviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "db_movies.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<MovieRepositoryImpl> { MovieRepository(get(), get(), get()) }
}