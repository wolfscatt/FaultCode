package com.tufar.faultcode.service

import com.tufar.faultcode.model.Combi
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CombiAPIService {

    // https://raw.githubusercontent.com/wolfscatt/combi_database/master/combi.json?token=GHSAT0AAAAAABZI55IORYRL7NFYJJIJIZVEY3HUMVQ

    // BASE_URL -> https://raw.githubusercontent.com/
    // wolfscatt/combi_database/master/combi.json?token=GHSAT0AAAAAABZI55IORYRL7NFYJJIJIZVEY3HUMVQ

    private val BASE_URL = "https://raw.githubusercontent.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CombiAPI::class.java)

    fun getData() : Single<List<Combi>>{
        return api.getCombi()
    }
}