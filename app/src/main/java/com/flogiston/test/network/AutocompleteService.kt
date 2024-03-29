package com.flogiston.test.network

import com.flogiston.test.network.autocompleteEntities.GeonamesResponce
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AutocompleteService {
    @GET("/searchJSON")
    fun getSuitableAddreses(
        @Query("q") q : String,
        @Query("maxRows") maxRows : Int,
        @Query("lang") lang : String,
        @Query("username") username : String
    ) : Observable<GeonamesResponce>
}