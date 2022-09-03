package com.mehdi.rahmani.homeCa

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.mehdi.rahmani.homeCa.Home.HomeFragment
import com.mehdi.rahmani.homeCa.Model.DataBase.DB
import com.mehdi.rahmani.homeCa.Splash.SplashFragment
import com.mehdi.rahmani.homeCa.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule


lateinit var db: DB

class MainActivity : AppCompatActivity() {

    lateinit var mainActivity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)

        // make DataBase Instance
        mkDataBase(mainActivity.root.context)


        // make splash
        supportFragmentManager.beginTransaction().replace(R.id.frame, SplashFragment()).commit()

        // after 3 sec go to home
        Timer().schedule(3000) {
            supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment()).commit()
        }

    }

    private fun mkDataBase(context: Context) {
        if (db == null) {
            db = Room.databaseBuilder(context, DB::class.java, "app_database")
                .allowMainThreadQueries().build()

            if (db.HomeDao().getHomesSize() == 0){

            }
        }
    }

}