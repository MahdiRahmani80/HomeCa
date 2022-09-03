package com.mehdi.rahmani.homeCa.Model.Objects

import androidx.room.*

@Entity(tableName = "NeighbourInCity")
class NeighbourInCity(
    @PrimaryKey val id: Int?,
    @Embedded(prefix = "_neighbour") val neighbour:Neighbourhood?,
    @Embedded(prefix = "_city") val city: City?
)