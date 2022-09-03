package com.mehdi.rahmani.homeCa.Model.Objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Neighbour")
data class Neighbourhood(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
)