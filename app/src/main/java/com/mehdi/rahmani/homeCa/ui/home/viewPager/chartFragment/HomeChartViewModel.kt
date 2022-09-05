package com.mehdi.rahmani.homeCa.ui.home.viewPager.chartFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.mehdi.rahmani.homeCa.data.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeChartViewModel(private val repo: HomeRepository) : ViewModel() {

    // LineChart
    private val homePrice: MutableLiveData<List<Entry>> by lazy {
        MutableLiveData<List<Entry>>()
    }
    private val homesArea: MutableLiveData<List<Entry>> by lazy {
        MutableLiveData<List<Entry>>()
    }

    // BarChart
    private val homePriceBar: MutableLiveData<List<BarEntry>> by lazy {
        MutableLiveData<List<BarEntry>>()
    }


    // Get Line Chart
    fun getHomeArea(position: Int): LiveData<List<Entry>> {
        getData(position, Data.AREA)
        return homesArea
    }
    fun getHomePrice(position: Int): LiveData<List<Entry>> {
        getData(position, Data.PRICE)
        return homePrice
    }

    // Get Bar Chart
    fun getBarPrice(position: Int):LiveData<List<BarEntry>>{
        getDataBarChart(position, Data.PRICE)
        return homePriceBar
    }

    // Line GetData
    private fun getData(position: Int, data: Data) {
        val list = ArrayList<Entry>()
        viewModelScope.launch {
            repo.getHomesInNeighbour(position).collect {
                for (i in it.indices) {

                    val d = when (data) {
                        Data.PRICE -> (it[i].home!!.price)!!.toFloat()
                        Data.AREA -> (it[i].home!!.area)!!.toFloat()
                    }

                    list.add(Entry(i.toFloat(), d))
                }
                when (data) {
                    Data.PRICE -> homePrice.postValue(list)
                    Data.AREA -> homesArea.postValue(list)
                }

            }
        }
    }

    //Bar Get Data
    private fun getDataBarChart(position: Int, data: Data) {
        val list = ArrayList<BarEntry>()
        viewModelScope.launch {
            repo.getHomesInNeighbour(position).collect {
                for (i in it.indices) {

                    val d = when (data) {
                        Data.PRICE -> (it[i].home!!.price)!!.toFloat()
                        Data.AREA -> (it[i].home!!.area)!!.toFloat()
                    }

                    list.add(BarEntry(i.toFloat(), d))
                }
                when (data) {
                    Data.PRICE -> homePriceBar.postValue(list)
                    Data.AREA -> homesArea.postValue(list)
                }

            }
        }
    }

    enum class Data {
        PRICE,
        AREA
    }

}