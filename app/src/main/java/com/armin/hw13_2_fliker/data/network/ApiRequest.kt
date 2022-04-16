package com.armin.hw13_2_fliker.data.network

import com.armin.hw13_2_fliker.data.network.model.Photo
import com.armin.hw13_2_fliker.data.network.model.PhotoJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("/services/rest/")
    fun getPhoto(
        @Query("api_key") api_Key: String,
        @Query("method") method: String,
        @Query("extras") extras: String,
        @Query("format") format : String,
        @Query("nojsoncallback") noJsonCallback : Int ,
        @Query("per_page") per_Page : Int ,
        @Query("page") page : Int
        ) : Call<PhotoJson>
}