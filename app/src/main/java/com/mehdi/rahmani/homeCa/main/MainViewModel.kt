package com.mehdi.rahmani.homeCa.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehdi.rahmani.homeCa.home.HomeFragment
import com.mehdi.rahmani.homeCa.model.dataBase.AppDatabase
import com.mehdi.rahmani.homeCa.model.dataBase.HomeDao
import com.mehdi.rahmani.homeCa.model.makeFakeData.MakeFakeData
import com.mehdi.rahmani.homeCa.model.objects.*
import com.mehdi.rahmani.homeCa.splash.SplashFragment
import java.util.*
import kotlin.concurrent.schedule

class MainViewModel : ViewModel() {

    private lateinit var dataBase: HomeDao
    private var _fr: Fragment? = null
    private val fr: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>().also {
            setFragment()
        }
    }


    private val fakeHome: MutableLiveData<List<Home>> by lazy {
        MutableLiveData<List<Home>>().also {
            MakeFakeData.mkRandomHome(dataBase)
        }
    }


    fun getFragment(): LiveData<Fragment> {
        if(_fr !is HomeFragment)
            fr.postValue(_fr)

        return fr
    }

    fun getHomeFakeData(homeDao: HomeDao): LiveData<List<Home>> {
        dataBase = homeDao
        return fakeHome
    }

    private fun setFragment() {

        _fr = SplashFragment()
        Timer().schedule(3000) {
            _fr = HomeFragment()
            fr.postValue(_fr)
        }
    }

}