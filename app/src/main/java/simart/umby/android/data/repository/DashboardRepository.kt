package simart.umby.android.data.repository

import retrofit2.Response
import simart.umby.android.data.remote.MyApi
import simart.umby.android.data.response.PengumumanResponse
import simart.umby.android.data.response.PlanetResponse

interface DashboardRepository {
    suspend fun doNetworkCall(page: Int): Response<PlanetResponse>
    suspend fun getPengumuman(): Response<PengumumanResponse>
}

class DashboardRepositoryImpl(
    private val api: MyApi
): DashboardRepository {
    override suspend fun doNetworkCall(page: Int): Response<PlanetResponse> = api.doNetworkCall(page)
    override suspend fun getPengumuman(): Response<PengumumanResponse> = api.getPengumuman()
}