package com.mehdi.rahmani.homeCa.Model.Objects

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "NeighbourInCity")
class NeighbourInCity(
    @PrimaryKey val id: Int,
    @Relation(
        parentColumn = "id",
        entityColumn = "city",
    ) val home: City,
    @Relation(
        parentColumn = "id",
        entityColumn = "neighbour",
    ) val neighbour: Neighbourhood,
)