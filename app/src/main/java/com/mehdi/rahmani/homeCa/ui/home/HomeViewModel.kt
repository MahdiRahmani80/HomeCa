package com.mehdi.rahmani.homeCa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehdi.rahmani.homeCa.data.local.HomeDao
import com.mehdi.rahmani.homeCa.data.repository.HomeRepository
import com.mehdi.rahmani.homeCa.model.City

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {


    private var _city: List<City>? = null
    private val viewPagerData: MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>()
    }

    fun getCity(): LiveData<List<City>> {

        if (_city != null) {
            viewPagerData.postValue(_city!!)
        } else if (_city !is List<City>) {
            // get city list in data base
            _city = repository.getCityList
            viewPagerData.postValue(_city!!)
        }
        return viewPagerData
    }
}