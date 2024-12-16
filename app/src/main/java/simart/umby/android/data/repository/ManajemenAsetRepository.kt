package simart.umby.android.data.repository

import retrofit2.Response
import simart.umby.android.data.remote.MyApi
import simart.umby.android.data.response.HistoryPersetujuanResponse
import simart.umby.android.data.response.PemindahanAsetResponse

interface ManajemenAsetRepository {
    suspend fun getPeminjamanAset(): List<String>
    suspend fun getPemindahanAset(): Response<PemindahanAsetResponse>
    suspend fun getHistoryPersetujuan(): Response<HistoryPersetujuanResponse>
}

class ManajemenAsetRepositoryImpl(private val api: MyApi): ManajemenAsetRepository {
    override suspend fun getPeminjamanAset(): List<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getPemindahanAset(): Response<PemindahanAsetResponse> = api.getPemindahanAset()
    override suspend fun getHistoryPersetujuan(): Response<HistoryPersetujuanResponse> = api.getHistoryPersetujuan()
}