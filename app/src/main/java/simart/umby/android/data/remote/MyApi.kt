package simart.umby.android.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import simart.umby.android.data.response.HistoryPersetujuanResponse
import simart.umby.android.data.response.PemindahanAsetResponse
import simart.umby.android.data.response.PengumumanResponse
import simart.umby.android.data.response.PlanetResponse
import simart.umby.android.model.login.LoginFormModel

interface MyApi {
    @GET("planets")
    suspend fun doNetworkCall(@Query("page") page: Int): Response<PlanetResponse>

    @POST("login")
    suspend fun login(@Body form: LoginFormModel): Response<String>

    @GET("pengumuman")
    suspend fun getPengumuman(): Response<PengumumanResponse>

    @GET("pemindahanAset")
    suspend fun getPemindahanAset(): Response<PemindahanAsetResponse>

    @GET("historyPersetujuan")
    suspend fun getHistoryPersetujuan(): Response<HistoryPersetujuanResponse>
}