package simart.umby.android.data.response

data class HistoryPersetujuanResponse (
    val status: Long? = null,
    val message: String? = null,
    val data: List<HistoryPersetujuanData>? = null
)

data class HistoryPersetujuanData (
    val id: Int? = null,
    val disetujuiOleh: String? = null,
    val status: String? = null,
    val tanggal: String? = null,
    val keterangan: String? = null
)
