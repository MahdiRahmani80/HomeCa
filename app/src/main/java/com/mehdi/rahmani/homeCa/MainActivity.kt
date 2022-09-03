package com.mehdi.rahmani.homeCa

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.room.Room
import com.mehdi.rahmani.homeCa.Home.HomeFragment
import com.mehdi.rahmani.homeCa.Model.DataBase.DB
import com.mehdi.rahmani.homeCa.Splash.SplashFragment
import com.mehdi.rahmani.homeCa.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    lateinit var mainActivity: ActivityMainBinding
    var db: DB? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)

        // set view model
        val mainVM: MainViewModel by viewModels()


        // make DataBase Instance
        mkDataBase(mainActivity.root.context, mainVM)


        // make splash
        supportFragmentManager.beginTransaction().replace(R.id.frame, SplashFragment()).commit()

        // after 3 sec go to home
        Timer().schedule(3000) {
            supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment()).commit()
        }

    }

    private fun mkDataBase(context: Context, mainVM: MainViewModel) {
        if (db == null) {
            db = Room.databaseBuilder(context, DB::class.java, "app_database")
                .allowMainThreadQueries().build()

            // if database is empty
            if (db!!.HomeDao().getHomesSize() == 0) {
                //make fake data
                mainVM.getHomeFakeData(db!!).observe(this) { data ->
                    for (home in data) db!!.HomeDao().addHome(home)
                }
            }
        }
    }

}