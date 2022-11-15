package com.tufar.faultcode.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tufar.faultcode.model.Combi


@Dao
interface CombiDAO {

    // Data Access Object  -  Veri Erişim Objesi

    @Insert
    suspend fun insertAll(vararg combi: Combi) : List<Long>

    // Insert -> Room, insert into
    // suspend -> coroutine scope
    // vararg -> birden fazla istediğimiz sayıda argüman verebilmemizi sağlıyor.
    // List<Long> -> uzun uuid lerimiz olduğu için long

    @Query("SELECT * FROM combi")
    suspend fun getAllCombi() : List<Combi>

    @Query("Select * From combi Where uuid = :combiId")
    suspend fun getCombi(combiId : Int) : Combi

    @Query("Select * from combi Where brand = :combiBrandName")
    suspend fun getCombiModel(combiBrandName : String?) : List<Combi>

    @Query("Select * from combi Where model = :combiModelName")
    suspend fun getCombiFault(combiModelName : String?) : List<Combi>

    @Query("Select * from combi Where faultCode = :combiFaultName")
    suspend fun getCombiDesc(combiFaultName : String?) : Combi

    @Query("Delete from combi")
    suspend fun deleteAllCombi()
}