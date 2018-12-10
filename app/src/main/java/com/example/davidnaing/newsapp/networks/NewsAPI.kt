package com.example.davidnaing.newsapp.networks

import com.example.davidnaing.newsapp.data.VOs.MainVO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/v2/top-headlines ")
    fun getNews(@Query("country") countryID : String,
                @Query("category") categoryID : String,
                @Query("apiKey") apiKey: String) : Observable<MainVO>
}