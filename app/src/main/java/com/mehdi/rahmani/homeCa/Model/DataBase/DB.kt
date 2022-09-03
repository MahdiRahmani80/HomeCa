package com.mehdi.rahmani.homeCa.Model.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mehdi.rahmani.homeCa.Model.Objects.*


@Database(
    entities = [Home::class, City::class, Neighbourhood::class, HomesInNeighbour::class, NeighbourInCity::class],
    version = 1
)
abstract class DB : RoomDatabase() {
    abstract fun HomeDao(): HomeDao

}


