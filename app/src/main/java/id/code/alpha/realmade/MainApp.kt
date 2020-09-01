package id.code.alpha.realmade

import android.app.Application
import id.code.alpha.core.di.databaseModule
import id.code.alpha.core.di.networkModule
import id.code.alpha.core.di.repositoryModule
import id.code.alpha.realmade.di.useCaseModule
import id.code.alpha.realmade.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}