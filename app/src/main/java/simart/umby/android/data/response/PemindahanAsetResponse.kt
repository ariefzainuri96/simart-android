package simart.umby.android.data.response

data class PemindahanAsetResponse (
    val status: Long? = null,
    val message: String? = null,
    val data: List<PemindahanAsetData>? = null
)

data class PemindahanAsetData (
    val noPemindahan: String? = null,
    val tanggal: String? = null,
    val lokasiAsal: String? = null,
    val lokasiTujuan: String? = null,
    val detailPeminjaman: DetailPeminjaman? = null,
    val historyPersetujuan: List<HistoryPersetujuan>? = null
)

data class DetailPeminjaman (
    val namaASET: String? = null,
    val jumlah: Long? = null,
    val satuan: String? = null,
    val lokasiAsal: String? = null,
    val lokasiTujuan: String? = null
)

data class HistoryPersetujuan (
    val disetujuiOleh: String? = null,
    val status: String? = null,
    val tanggal: String? = null,
    val keterangan: String? = null
)