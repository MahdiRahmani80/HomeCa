package com.mehdi.rahmani.homeCa.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehdi.rahmani.homeCa.data.local.HomeDao
import com.mehdi.rahmani.homeCa.model.*


@Database(
    entities = [Home::class, City::class, Neighbourhood::class, HomesInNeighbour::class, NeighbourInCity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun HomeDao(): HomeDao

}


