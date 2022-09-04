package com.mehdi.rahmani.homeCa.data.repository


import com.mehdi.rahmani.homeCa.data.local.HomeDao
import com.mehdi.rahmani.homeCa.model.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val homeDao: HomeDao) {

    val homeCount = homeDao.getHomesSize()

    val getCityList = homeDao.getCity()

}