package com.mehdi.rahmani.homeCa.model.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehdi.rahmani.homeCa.model.objects.*


@Database(
    entities = [Home::class, City::class, Neighbourhood::class, HomesInNeighbour::class, NeighbourInCity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun HomeDao(): HomeDao

}


