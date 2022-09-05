package com.mehdi.rahmani.homeCa

import android.app.Application
import androidx.room.Room
import com.mehdi.rahmani.homeCa.data.AppDatabase
import com.mehdi.rahmani.homeCa.data.local.HomeDao
import com.mehdi.rahmani.homeCa.data.repository.HomeRepository
import com.mehdi.rahmani.homeCa.ui.home.HomeViewModel
import com.mehdi.rahmani.homeCa.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val myModules = module {

            single<AppDatabase> {
                Room.databaseBuilder(this@App, AppDatabase::class.java, "app_database")
                    .allowMainThreadQueries().build()
            }
            single<HomeDao> {
                val database = get<AppDatabase>()
                database.HomeDao()
            }

            // view models
            single {  MainViewModel(get(), get()) }
            single{ HomeViewModel(get()) }
            single{ HomeRepository(get()) }

        }


        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(myModules)
        }
    }
}