package com.mehdi.rahmani.homeCa.Model.Objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home")
data class Home(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "build_year") val build_year: Int,
    @ColumnInfo(name = "doc") val doc: Boolean,
    @ColumnInfo(name = "area") val area: Int,
    @ColumnInfo(name = "price") val price: Int,
)