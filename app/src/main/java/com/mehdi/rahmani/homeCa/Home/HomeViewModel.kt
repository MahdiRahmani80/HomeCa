package com.mehdi.rahmani.homeCa.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mehdi.rahmani.homeCa.Model.Objects.City
import com.mehdi.rahmani.homeCa.Model.Objects.NeighbourInCity
import com.mehdi.rahmani.homeCa.db
import java.util.*
import kotlin.concurrent.schedule

class HomeViewModel() : ViewModel() {

    private val viewPagerData: MutableLiveData<List<City>> by lazy {
        MutableLiveData<List<City>>().also {
            getDataFromDb()
        }
    }

    fun getNeighbours(): LiveData<List<City>> = viewPagerData

    private fun getDataFromDb() {

        Timer().schedule(100) {
            val data = db!!.HomeDao().getCity()
            viewPagerData.postValue(data)
        }
    }

}