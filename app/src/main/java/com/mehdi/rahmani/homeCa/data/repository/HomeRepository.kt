package com.mehdi.rahmani.homeCa.data.repository


import android.util.Log
import com.mehdi.rahmani.homeCa.data.local.HomeDao
import com.mehdi.rahmani.homeCa.model.City
import com.mehdi.rahmani.homeCa.model.Home
import com.mehdi.rahmani.homeCa.model.HomesInNeighbour
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository constructor(private val homeDao: HomeDao) {

    suspend fun getHomeSize(): Flow<Int> {
        return flow {

            try {
                emit(homeDao.getHomesSize())
            } catch (e: Exception) {
                Log.e("ErrorGetHomeSize", "$e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCityList(): Flow<List<City>> {
        return flow {

            try {
                emit(homeDao.getCity())
            } catch (e: Exception) {
                Log.e("ErrorGetHomeList", "$e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getHomesInNeighbour(position:Int): Flow<List<HomesInNeighbour>> {
        return flow {

            try {
                emit(homeDao.getHomesInNeighbour(position))
            } catch (e: Exception) {
                Log.e("ErrorGetHomeList", "$e")
            }
        }.flowOn(Dispatchers.IO)
    }
}
