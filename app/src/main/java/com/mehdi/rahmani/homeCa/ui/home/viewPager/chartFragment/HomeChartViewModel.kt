package com.mehdi.rahmani.homeCa.ui.home.viewPager.chartFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.mehdi.rahmani.homeCa.data.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeChartViewModel(private val repo: HomeRepository) : ViewModel() {

    private val homesInNeighbour: MutableLiveData<List<Entry>> by lazy {
        MutableLiveData<List<Entry>>().also {
            getData()
        }

    }

    fun getHomeInNeighbour() = homesInNeighbour

    private fun getData() {
        val list = ArrayList<Entry>()
        viewModelScope.launch {
            repo.getHomesInNeighbour().collect {
                for (i in it) {
                    val id: Float = (i.id)!!.toFloat()
                    val price = (i.home!!.price)!!.toFloat()
                    list.add(Entry(id, price))
                }
            }
            homesInNeighbour.postValue(list)
        }
    }



}