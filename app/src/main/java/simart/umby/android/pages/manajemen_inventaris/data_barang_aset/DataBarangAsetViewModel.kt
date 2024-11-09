package simart.umby.android.pages.manajemen_inventaris.data_barang_aset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import simart.umby.android.pages.manajemen_inventaris.data_barang_aset.model.DataBarangAsetModel
import simart.umby.android.utils.RequestState

class DataBarangAsetViewModel: ViewModel() {
    private var _listDataBarang = MutableStateFlow<List<DataBarangAsetModel>>(listOf())
    var listDataBarang = _listDataBarang.asStateFlow()
    
    private var _state = MutableStateFlow(RequestState.IDLE)
    var state = _state.asStateFlow()
    
    fun getDataBarangAset() {
        _state.value = RequestState.LOADING
        
        viewModelScope.launch {
            delay(1000L)
            
            _listDataBarang.value = listOf<DataBarangAsetModel>(
                DataBarangAsetModel(noInventaris = "INV-GR-FTI-2024", namaAset = "Komputer aset untuk Admin 1 kantor kampus II", jumlahAset = "12", sumberAset = "Ruang 4D", deskripsiAset = "Lorem ipsum dolor sit amet consectetur. Dignissim lacus gravida porttitor potenti justo.", spesifikasi = "Apple MacBook Air M1\n" +
                        "Layar : 13,3 inci IPS (2560 x 1600 piksel) 227 ppi\n" +
                        "Prosesor : chip Apple M1\n" +
                        "Kartu grafik :-\n" +
                        "RAM : 8 GB (dapat diperluas hingga 16 GB)\n" +
                        "Penyimpanan : SSD 256 GB atau 512 GB (dapat diperluas hingga 2 TB)\n" +
                        "Optik : Tidak\n" +
                        "Konektivitas : Wi-Fi 6 802.11ax, Bluetooth versi 5.0\n" +
                        "Port : 2x Thunderbolt/USB 4 (Thunderbolt 3 hingga 40 Gb/s dan USB 3.1 Gen hingga 10 Gb/s)\n" +
                        "Baterai : Li-Polimer, 49,9 Wh")
            )

            _state.value = RequestState.SUCCESS
        }
    }
    
    init {
        getDataBarangAset()
    }
}