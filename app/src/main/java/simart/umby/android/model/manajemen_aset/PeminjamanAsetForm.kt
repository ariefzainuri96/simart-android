package simart.umby.android.model.manajemen_aset

data class PeminjamanAsetForm(
    val noPeminjam: String = "", val tipePeminjam: String = "", val identitasPeminjam:
    String = "", val namaPeminjam: String = "", val alamatPeminjam: String = "", val
    fakultas: String = "", val noTelepon: String = "", val tanggalPinjam: String = "",
    val tanggalKembali: String = ""
)

data class DetailPeminjamanAsetForm(
    val namaAset: String = "", val jumlah: String = "", val satuan: String = "",
)
