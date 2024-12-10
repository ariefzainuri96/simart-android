package simart.umby.android.pages.manajemen_aset.peminjaman_aset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import simart.umby.android.model.manajemen_aset.PeminjamanAsetModel
import simart.umby.android.utils.RequestState

class PeminjamanAsetViewModel: ViewModel() {
    var peminjamanAsetList = MutableStateFlow(listOf<PeminjamanAsetModel>()); private set
    var peminjamanAsetState = MutableStateFlow(RequestState.IDLE); private set

    fun getPeminjamanAset() {
        peminjamanAsetState.value = RequestState.LOADING

        viewModelScope.launch {
            delay(1500L)

            peminjamanAsetList.value += listOf<PeminjamanAsetModel>(
                PeminjamanAsetModel("namaPeminjam", "noPeminjam", "tipePeminjam",
                    "noTeleponPeminjam",
                    "tanggalPinjam", "statusApproval", "tanggalKembali", "statusPeminjaman",)
            )

            peminjamanAsetState.value = RequestState.SUCCESS
        }
    }

    init {
        getPeminjamanAset()
    }
}