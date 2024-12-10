package simart.umby.android.model.manajemen_inventaris

data class DataBarangAsetModel(
    var noInventaris: String? = null, var namaAset: String? = null, var deskripsiAset: String? = null,
    var spesifikasi: String? = null, var isAsetSPK: Boolean? = null, var tipeAset: String? = null, var noSPK: String? = null,
    var vendor: String? = null, var kategoriAset: String? = null, var subKategoriAset: String? = null,
    var kampus: String? = null, var ruang: String? = null, var convidentality: String? = null,
    var integrity: String? = null, var availability: String? = null, var tanggalAkuisisi: String? = null,
    var tanggalDepresiasi: String? = null, var asetTerdepresiasi: String? = null, var sumberAset: String? = null,
    var jumlahAset: String? = null, var nvb: String? = null, var statusAset: String? = null
    )
