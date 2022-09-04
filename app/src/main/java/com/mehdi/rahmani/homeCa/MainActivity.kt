package com.mehdi.rahmani.homeCa

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.room.Room
import com.mehdi.rahmani.homeCa.model.dataBase.AppDatabase
import com.mehdi.rahmani.homeCa.databinding.ActivityMainBinding

var db: AppDatabase? = null
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
        mainVM.getFragment().observe(this){
            supportFragmentManager.beginTransaction().replace(R.id.frame, it ).commit()
        }

    }

    private fun mkDataBase(context: Context, mainVM: MainViewModel) {
        if (db == null) {
            db = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
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