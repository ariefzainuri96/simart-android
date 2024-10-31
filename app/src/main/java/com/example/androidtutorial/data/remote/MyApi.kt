package com.example.androidtutorial.data.remote

import com.example.androidtutorial.data.response.PlanetResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("planets")
    suspend fun doNetworkCall(@Query("page") page: Int): Response<PlanetResponse>
}