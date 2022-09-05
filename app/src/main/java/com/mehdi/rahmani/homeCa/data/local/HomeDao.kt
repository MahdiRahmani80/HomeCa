package com.mehdi.rahmani.homeCa.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mehdi.rahmani.homeCa.model.*

@Dao
interface HomeDao {

    @Query("SELECT * ,COUNT(*) FROM home")
    fun getHomesSize():Int

    @Query("SELECT * FROM home")
    fun getHomes():List<Home>

    @Query("SELECT * FROM 'HomesInNeighbour' WHERE neighbour_id = (:position)  ;")
    fun getHomesInNeighbour(position:Int):List<HomesInNeighbour>

    @Query("SELECT * FROM NeighbourInCity")
    fun getNeighbourInCity():List<NeighbourInCity>

    @Query("SELECT * FROM city")
    fun getCity():List<City>

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