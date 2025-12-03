package com.bignerdranch.android.zd2_karamov

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item : Movie)
    @Query("SELECT * FROM Movies")
    fun getAllItems() : Flow<List<Movie>>
    @Delete
    fun delete(item : Movie)
    @Update
    suspend fun update(movie: Movie)
}