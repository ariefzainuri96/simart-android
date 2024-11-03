package simart.umby.android.data.repository

import retrofit2.Response
import simart.umby.android.data.remote.MyApi
import simart.umby.android.data.response.PlanetResponse

class DashboardRepositoryImpl(
    private val api: MyApi
): DashboardRepository {
    override suspend fun doNetworkCall(page: Int): Response<PlanetResponse> = api.doNetworkCall(page)
}

interface DashboardRepository {
    suspend fun doNetworkCall(page: Int): Response<PlanetResponse>
}