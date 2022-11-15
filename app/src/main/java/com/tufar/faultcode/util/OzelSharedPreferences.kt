package com.tufar.faultcode.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class OzelSharedPreferences {

    companion object{
        private val ZAMAN = "zaman"
        private var sharedPreferences : SharedPreferences? = null

        @Volatile private var instance : OzelSharedPreferences ?= null

        private val lock = Any()
        operator fun invoke(context: Context) : OzelSharedPreferences = instance ?: synchronized(
            lock){
            instance ?: makeOzelSharedPreferences(context).also{
                instance = it
            }
        }

        private fun makeOzelSharedPreferences(context: Context): OzelSharedPreferences{
            sharedPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPreferences()
        }
    }

    fun saveTime(zaman:Long){
        sharedPreferences?.edit(commit = true){
            putLong(ZAMAN,zaman)
        }
    }

    fun getTime() = sharedPreferences?.getLong(ZAMAN,0)
}