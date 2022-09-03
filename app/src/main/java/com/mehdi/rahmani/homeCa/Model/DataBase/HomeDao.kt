package com.mehdi.rahmani.homeCa.Model.DataBase

import androidx.room.Dao
import androidx.room.Query

@Dao
interface HomeDao {

    @Query("SELECT * ,COUNT(*) FROM home")
    fun getHomesSize():Int
}