package simart.umby.android.model.manajemen_aset

data class PeminjamanAsetModel(
    val namaPeminjam: String,
    val noPeminjaman: String,
    val tipePeminjam: String,
    val noTeleponPeminjam: String,
    val tanggalPinjam: String,
    val statusApproval: String,
    val tanggalKembali: String,
    val statusPeminjaman: String
)
