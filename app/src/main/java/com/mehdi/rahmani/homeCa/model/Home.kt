package com.mehdi.rahmani.homeCa.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home")
data class Home(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "home_build_year") val build_year: Int?,
    @ColumnInfo(name = "home_document") val document: Boolean?,
    @ColumnInfo(name = "home_area") val area: Int?,
    @ColumnInfo(name = "home_price") val price: Int?,
)