package com.tufar.faultcode.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Combi(
    @ColumnInfo(name = "brand")
    @SerializedName("brand")
    val brand : String?,

    @ColumnInfo(name = "model")
    @SerializedName("model")
    val model: String?,

    @ColumnInfo(name = "faultCode")
    @SerializedName("faultCode")
    val faultCode : String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description : String?) {

    @PrimaryKey(autoGenerate = true)
    var uuid :Int = 0
}