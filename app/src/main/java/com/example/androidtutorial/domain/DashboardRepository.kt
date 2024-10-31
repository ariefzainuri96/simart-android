package com.example.androidtutorial.domain

import com.example.androidtutorial.data.response.PlanetResponse
import retrofit2.Response

interface DashboardRepository {
    suspend fun doNetworkCall(page: Int): Response<PlanetResponse>
}