package com.tufar.faultcode.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tufar.faultcode.model.Combi

@Database(entities = arrayOf(Combi::class), version = 1)
abstract class CombiDatabase : RoomDatabase() {

    abstract fun combiDao() : CombiDAO

    // Singleton

    companion object{

        @Volatile private var instance : CombiDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CombiDatabase::class.java,
            "combidatabase").build()
    }
}