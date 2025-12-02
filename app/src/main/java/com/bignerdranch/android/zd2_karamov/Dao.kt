package com.bignerdranch.android.zd2_karamov

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(item : Movie)
    @Query("SELECT * FROM Movies")
    fun getAllItems() : Flow<List<Movie>>
    @Delete
    fun delete(item : Movie)
    @Query("DELETE FROM Movies")
    fun clearAllItems()
}