package com.mehdi.rahmani.homeCa.ui.home.viewPager.chartFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.mehdi.rahmani.homeCa.data.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeChartViewModel(private val repo: HomeRepository) : ViewModel() {

    private val homesInNeighbour: MutableLiveData<List<Entry>> by lazy {
        MutableLiveData<List<Entry>>()
    }

    fun getHomeInNeighbour(position:Int) :LiveData<List<Entry>> {
        getData(position)
        return homesInNeighbour
    }

    private fun getData(position: Int) {
        val list = ArrayList<Entry>()
        viewModelScope.launch {
            repo.getHomesInNeighbour(position).collect {
                for (i in it.indices) {

                    val price = (it[i].home!!.price)!!.toFloat()
                    list.add(Entry(i.toFloat(), price))
                }
                homesInNeighbour.postValue(list)
            }
        }
    }



}