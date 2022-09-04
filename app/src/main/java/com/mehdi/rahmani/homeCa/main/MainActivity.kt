package com.mehdi.rahmani.homeCa.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.mehdi.rahmani.homeCa.R
import com.mehdi.rahmani.homeCa.model.dataBase.AppDatabase
import com.mehdi.rahmani.homeCa.databinding.ActivityMainBinding
import com.mehdi.rahmani.homeCa.model.dataBase.HomeDao
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    lateinit var mainActivity: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivity.root)
        super.onCreate(savedInstanceState)

        // set view model
        val mainVM: MainViewModel by viewModels()


        // make DataBase Instance
        mkDataBase(mainActivity.root.context, mainVM)


        // show fragments
        mainVM.getFragment().observe(this) {
            supportFragmentManager.beginTransaction().replace(R.id.frame, it).commit()
        }

    }

    private fun mkDataBase(context: Context, mainVM: MainViewModel) {

        val homeDao: HomeDao by inject()
        // if database is empty
        if (homeDao.getHomesSize() == 0) {
            //make fake data
            mainVM.getHomeFakeData(homeDao).observe(this) { data ->
                for (home in data) homeDao.addHome(home)
            }
        }

    }

}