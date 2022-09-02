package com.mehdi.rahmani.homeCa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mehdi.rahmani.homeCa.Home.HomeFragment
import com.mehdi.rahmani.homeCa.Splash.SplashFragment
import com.mehdi.rahmani.homeCa.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    lateinit var mainActivity: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)



        // make splash
        supportFragmentManager.beginTransaction().replace(R.id.frame,SplashFragment()).commit()

        // after 3 sec go to home
        Timer().schedule( 3000 ){
            supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment()).commit()
        }

    }
}