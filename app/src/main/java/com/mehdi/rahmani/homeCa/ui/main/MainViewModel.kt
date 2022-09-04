package com.mehdi.rahmani.homeCa.ui.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.mehdi.rahmani.homeCa.ui.home.HomeFragment
import com.mehdi.rahmani.homeCa.data.local.HomeDao
import com.mehdi.rahmani.homeCa.data.makeFakeData.MakeFakeData
import com.mehdi.rahmani.homeCa.data.repository.HomeRepository
import com.mehdi.rahmani.homeCa.model.Home
import com.mehdi.rahmani.homeCa.ui.splash.SplashFragment
import java.util.*
import kotlin.concurrent.schedule
import kotlin.reflect.KProperty

class MainViewModel(private val repo: HomeRepository, private val homeDao: HomeDao) : ViewModel() {


    private var _fr: Fragment? = null
    private val fr: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>().also {
            setFragment()
        }
    }


    private val fakeHome: MutableLiveData<List<Home>> by lazy {
        MutableLiveData<List<Home>>().also {
            MakeFakeData.mkRandomHome(homeDao)
        }
    }


    fun getFragment(): LiveData<Fragment> {
        if (_fr !is HomeFragment)
            fr.postValue(_fr)

        return fr
    }


    fun getHomeCount() = repo.homeCount
    fun setFakeHome(){ MakeFakeData.mkRandomHome(homeDao)   }

    private fun setFragment() {

        _fr = SplashFragment()
        Timer().schedule(3000) {
            _fr = HomeFragment()
            fr.postValue(_fr)
        }
    }


}