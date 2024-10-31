package com.example.androidtutorial.data.repository

import android.app.Application
import com.example.androidtutorial.data.remote.MyApi
import com.example.androidtutorial.data.response.PlanetResponse
import com.example.androidtutorial.domain.DashboardRepository
import retrofit2.Response

class DashboardRepositoryImpl(
    private val api: MyApi,
    private val appContext: Application
): DashboardRepository {
    override suspend fun doNetworkCall(page: Int): Response<PlanetResponse> = api.doNetworkCall(page)
}