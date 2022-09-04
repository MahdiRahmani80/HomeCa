package com.mehdi.rahmani.homeCa.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mehdi.rahmani.homeCa.R
import com.mehdi.rahmani.homeCa.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var mainActivity: ActivityMainBinding

    // set view model
    private val mainVM: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)
        super.onCreate(savedInstanceState)


        // set FakeData if DataBase is empty
        setData(mainVM)

        // show fragments
        mainVM.getFragment().observe(this) {
            supportFragmentManager.beginTransaction().replace(R.id.frame, it).commit()
        }

    }

    private fun setData(mainVM: MainViewModel) {

        // if database is empty
        if (mainVM.getHomeCount() == 0) {

            //make fake data
            mainVM.setFakeHome()
        }

    }

}