package com.tufar.faultcode.service

import com.tufar.faultcode.model.Combi
import io.reactivex.Single
import retrofit2.http.GET

interface CombiAPI {

    // Get isteği

    // https://raw.githubusercontent.com/wolfscatt/CombiDataSet/main/combi_brand.json?token=GHSAT0AAAAAABZI55IPR66MVAFA6JHOD54EY3GJSTA

    // BASE_URL -> https://raw.githubusercontent.com/
    // wolfscatt/combi_database/master/combi.json?token=GHSAT0AAAAAABZI55IORYRL7NFYJJIJIZVEY3HUMVQ

    @GET("wolfscatt/combi_database/master/combi.json")
    fun getCombi(): Single<List<Combi>>
}