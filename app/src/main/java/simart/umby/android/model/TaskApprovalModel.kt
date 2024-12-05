package simart.umby.android.model

data class TaskApprovalModel(
    val title: String = "", val namaAset: String = "", val noPeminjamanAset: String = "", val date:
    String = "",
    val status: String = "", val tipePeminjam: String = "", val identitasPeminjam: String = "",
    val namaPeminjam: String = "", val alamatPeminjam: String = "", val fakultas: String = "",
    val noHP: String = "", val tanggalPinjam: String = "", val tanggalKembali: String = "",
    val detailPeminjaman: List<DetailPeminjamanAsetModel> = listOf(),
    val historyPersetujuan: List<HistoryPersetujuanModel> = listOf()
)

data class DetailPeminjamanAsetModel(
    val namaAset: String, val jumlahAset: String
)

data class HistoryPersetujuanModel(
    val disetujuiOleh: String, val status: String, val tanggal: String, val keterangan: String
)
