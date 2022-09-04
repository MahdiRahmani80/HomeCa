package com.mehdi.rahmani.homeCa.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HomesInNeighbour")
data class HomesInNeighbour(
    @PrimaryKey val id: Int?,
    @Embedded(prefix = "_home") val home: Home?,
    @Embedded(prefix = "_neighbour") val neighbour: Neighbourhood?
)