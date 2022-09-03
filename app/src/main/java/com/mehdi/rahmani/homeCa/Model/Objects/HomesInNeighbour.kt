package com.mehdi.rahmani.homeCa.Model.Objects

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import androidx.room.PrimaryKey

@Entity(tableName = "HomesInNeighbour")
data class HomesInNeighbour(
    @PrimaryKey val id: Int?,
    @Embedded(prefix = "_home") val home:Home?,
    @Embedded(prefix = "_neighbour") val neighbour:Neighbourhood?
)