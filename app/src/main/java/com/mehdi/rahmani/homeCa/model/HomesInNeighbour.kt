package com.mehdi.rahmani.homeCa.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HomesInNeighbour")
data class HomesInNeighbour(
    @PrimaryKey val id: Int?,
    @Embedded(prefix = "home_") val home: Home?,
    @Embedded(prefix = "neighbour_") val neighbour: Neighbourhood?
)