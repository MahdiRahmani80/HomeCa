package com.mehdi.rahmani.homeCa.Model.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mehdi.rahmani.homeCa.Model.Objects.*

@Dao
interface HomeDao {

    @Query("SELECT * ,COUNT(*) FROM home")
    fun getHomesSize():Int

    // Inserts
    @Insert(entity= Home::class)
    fun addHome(home: Home)

    @Insert(entity= City::class)
    fun addCity(city: City)

    @Insert(entity= Neighbourhood::class)
    fun addNeighbour(neighbour: Neighbourhood)

    @Insert(entity= NeighbourInCity::class)
    fun addNeighbourInCity(neighbourInCity: NeighbourInCity)

    @Insert(entity= HomesInNeighbour::class)
    fun addHomeInNeighbour(homesInNeighbour: HomesInNeighbour)
}