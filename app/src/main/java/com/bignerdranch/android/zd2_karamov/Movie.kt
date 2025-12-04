package com.bignerdranch.android.zd2_karamov

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "image")
    val image : String,
    @ColumnInfo(name = "favorite")
    var favorite : Boolean
)