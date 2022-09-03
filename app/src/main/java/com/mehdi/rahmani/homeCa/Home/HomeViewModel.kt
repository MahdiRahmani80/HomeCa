package com.mehdi.rahmani.homeCa.Home

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehdi.rahmani.homeCa.Model.Objects.City
import com.mehdi.rahmani.homeCa.Splash.SplashFragment
import com.mehdi.rahmani.homeCa.db
import java.util.*
import kotlin.concurrent.schedule

class HomeViewModel() : ViewModel() {


    private var _city:List<City>?=null
    private val viewPagerData: MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>()
    }

    fun getCity(): LiveData<List<City>>  {
        if (_city!=null) {
            viewPagerData.postValue(_city!!)
        }else if (_city !is List<City> ){
            _city = db!!.HomeDao().getCity()
            viewPagerData.postValue(_city!!)
        }
        return viewPagerData
    }




}