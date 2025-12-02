package com.bignerdranch.android.zd2_karamov

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Movie::class], version = 1)
abstract class MainDB : RoomDatabase() {
    abstract fun getDao() : Dao
    companion object{
        fun getDb(context: Context): MainDB
        {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "fav.db"
            ).build()
        }
    }
}