package simart.umby.android.data.repository

import kotlinx.coroutines.delay
import simart.umby.android.data.remote.MyApi
import simart.umby.android.model.DetailPeminjamanAsetModel
import simart.umby.android.model.HistoryPersetujuanModel
import simart.umby.android.model.TaskApprovalModel

interface TaskApprovalRepository {
    suspend fun getDetailPeminjamanAset(): TaskApprovalModel?
}

class TaskApprovalRepositoryImpl(
    private val api: MyApi
) : TaskApprovalRepository {
    override suspend fun getDetailPeminjamanAset(): TaskApprovalModel? {
        delay(1500L)

        return TaskApprovalModel(
            noPeminjamanAset = "123123123",
            namaAset = "Nama Aset",
            namaPeminjam = "Arief Zainuri",
            tanggalKembali = "06/10/2024",
            tanggalPinjam = "06/08/2024",
            identitasPeminjam = "KTP",
            alamatPeminjam = "Jl. Jend. Sudirman, Gowongan, Kec. Jetis, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55233",
            fakultas = "Sistem Informasi",
            noHP = "08128712",
            tipePeminjam = "Mahasiswa",
            detailPeminjaman = listOf(
                DetailPeminjamanAsetModel(
                    namaAset = "Nama Aset",
                    jumlahAset = "1 Unit"
                ),
                DetailPeminjamanAsetModel(
                    namaAset = "Nama Aset",
                    jumlahAset = "1 Unit"
                )
            ),
            historyPersetujuan = listOf(
                HistoryPersetujuanModel(
                    disetujuiOleh = "Arief Zainuri",
                    status = "Disetujui",
                    tanggal = "06/08/2024",
                    keterangan = "Lorem ipsum dolor sit amet asdkajsdkjasd consectuer"
                )
            )
        )
    }
}