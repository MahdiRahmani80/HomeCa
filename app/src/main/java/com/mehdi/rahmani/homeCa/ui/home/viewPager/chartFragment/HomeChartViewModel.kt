package com.mehdi.rahmani.homeCa.ui.home.viewPager.chartFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieEntry
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

    // PieChart
    private val homePricePie: MutableLiveData<List<PieEntry>> by lazy {
        MutableLiveData<List<PieEntry>>()
    }

    // ScatterChart
    private val candlePrice: MutableLiveData<List<BarEntry>> by lazy {
        MutableLiveData<List<BarEntry>>()
    }


    fun getScatterChart(position: Int): LiveData<List<BarEntry>> {
        getCandleData(position, Data.PRICE)
        return candlePrice
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

    // Get PieChart
    fun getPiePrice(position: Int): LiveData<List<PieEntry>> {
        getPieData(position, Data.PRICE)
        return homePricePie
    }

    // Get Bar Chart
    fun getBarPrice(position: Int): LiveData<List<BarEntry>> {
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

    //Bar Get Data
    private fun getPieData(position: Int, data: Data) {
        val list = ArrayList<PieEntry>()
        viewModelScope.launch {
            repo.getHomesInNeighbour().collect {

                val n = ArrayList<Float>()
                n.add(0F)
                n.add(0F)
                n.add(0F)
                for (i in it) {
                    when (i.neighbour!!.id) {
                        0 -> n[0] += 1.0F
                        1 -> n[1] += 1.0F
                        2 -> n[2] += 1.0F
                    }
                }

                for (j in (0..2)) {
                    list.add(PieEntry(n[j], it[j].neighbour!!.name))
                }

                when (data) {
                    Data.PRICE -> homePricePie.postValue(list)
                    Data.AREA -> homesArea.postValue(list)
                }

            }
        }
    }

    private fun getCandleData(position: Int, data: Data) {
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
                    Data.PRICE -> candlePrice.postValue(list)
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