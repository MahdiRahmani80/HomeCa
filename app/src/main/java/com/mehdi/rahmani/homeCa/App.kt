package com.mehdi.rahmani.homeCa

import android.app.Application
import androidx.room.Room
import com.mehdi.rahmani.homeCa.model.dataBase.AppDatabase
import com.mehdi.rahmani.homeCa.model.dataBase.HomeDao
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}