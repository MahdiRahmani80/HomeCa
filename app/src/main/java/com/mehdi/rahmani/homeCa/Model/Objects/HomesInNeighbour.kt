package com.mehdi.rahmani.homeCa.Model.Objects

import androidx.room.Entity
import androidx.room.Relation
import androidx.room.PrimaryKey

@Entity(tableName = "HomesInNeighbour")
data class HomesInNeighbour(
    @PrimaryKey val id: Int,
    @Relation(
        parentColumn = "id",
        entityColumn = "home",
    ) val home:Home,
    @Relation(
        parentColumn = "id",
        entityColumn = "neighbour",
    ) val neighbour:Neighbourhood,
)