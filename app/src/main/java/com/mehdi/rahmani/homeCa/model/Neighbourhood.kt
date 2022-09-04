package com.mehdi.rahmani.homeCa.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Neighbour")
data class Neighbourhood(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "neighbourhood_name") val name: String?
)