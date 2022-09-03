package com.mehdi.rahmani.homeCa.Model.Objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class City(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="name") val city_name:String
    )