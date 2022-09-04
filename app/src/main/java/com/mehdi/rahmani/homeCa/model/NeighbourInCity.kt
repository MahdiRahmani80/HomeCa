package com.mehdi.rahmani.homeCa.model

import androidx.room.*
import com.mehdi.rahmani.homeCa.model.City
import com.mehdi.rahmani.homeCa.model.Neighbourhood

@Entity(tableName = "NeighbourInCity")
class NeighbourInCity(
    @PrimaryKey val id: Int?,
    @Embedded(prefix = "_neighbour") val neighbour: Neighbourhood?,
    @Embedded(prefix = "_city") val city: City?
)