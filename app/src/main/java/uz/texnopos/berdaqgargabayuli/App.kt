package uz.texnopos.berdaqgargabayuli

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import uz.texnopos.berdaqgargabayuli.di.repositoryModule
import uz.texnopos.berdaqgargabayuli.di.roomModule
import uz.texnopos.berdaqgargabayuli.di.viewModelModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        //Koin
        val modules = listOf(roomModule, repositoryModule, viewModelModule)
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            koin.loadModules(modules)
        }

    }
}