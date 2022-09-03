package com.mehdi.rahmani.homeCa

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehdi.rahmani.homeCa.Home.HomeFragment
import com.mehdi.rahmani.homeCa.Model.DataBase.DB
import com.mehdi.rahmani.homeCa.Model.Objects.*
import com.mehdi.rahmani.homeCa.Splash.SplashFragment
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private lateinit var dataBase: DB
    private var _fr: Fragment? = null
    private val fr: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>().also {
            setFragment()
        }
    }


    private val fakeHome: MutableLiveData<List<Home>> by lazy {
        MutableLiveData<List<Home>>().also {
            mkRandomHome()
        }
    }


    fun getFragment(): LiveData<Fragment> {
        if(_fr !is HomeFragment)
            fr.postValue(_fr)

        return fr
    }

    fun getHomeFakeData(db: DB): LiveData<List<Home>> {
        dataBase = db
        return fakeHome
    }

    private fun setFragment() {

        _fr = SplashFragment()
        Timer().schedule(3000) {
            _fr = HomeFragment()
            fr.postValue(_fr)
        }
    }

    private fun mkRandomHome() {

        val cityList = ArrayList<City>()
        val neighbourList = ArrayList<Neighbourhood>()

        //add city
        for (i in (0..2)) {
            var city: String?
            if (i == 0) {
                city = "تهران"
            } else if (i == 1) {
                city = "تبریز"
            } else {
                city = "ارومیه"
            }
            val c = City(i, city)
            cityList.add(c)
            dataBase.HomeDao().addCity(c)
        }
        // add neighbour

        for (i in (0..2)) {
            val n = Neighbourhood(i, "محله $i")
            neighbourList.add(n)
            dataBase.HomeDao().addNeighbour(n)
        }

        // add home
        for (i in (0..300)) {
            val randYear = Random.nextInt(1995, 2022)
            val doc = if (randYear % 2 == 0) true else false
            val area = randYear * i / 1000
            val h = Home(i, randYear, doc, area, area * i * randYear)
            dataBase.HomeDao().addHome(h)
            dataBase.HomeDao().addHomeInNeighbour(HomesInNeighbour(i, h, neighbourList[i % 3]))
            dataBase.HomeDao()
                .addNeighbourInCity(NeighbourInCity(i, neighbourList[i % 3], cityList[i % 3]))
        }

    }
}