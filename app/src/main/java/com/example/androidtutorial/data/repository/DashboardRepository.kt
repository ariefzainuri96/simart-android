package com.example.androidtutorial.data.repository

import com.example.androidtutorial.data.remote.MyApi
import com.example.androidtutorial.data.response.PlanetResponse
import retrofit2.Response

class DashboardRepositoryImpl(
    private val api: MyApi
): DashboardRepository {
    override suspend fun doNetworkCall(page: Int): Response<PlanetResponse> = api.doNetworkCall(page)
}

interface DashboardRepository {
    suspend fun doNetworkCall(page: Int): Response<PlanetResponse>
}