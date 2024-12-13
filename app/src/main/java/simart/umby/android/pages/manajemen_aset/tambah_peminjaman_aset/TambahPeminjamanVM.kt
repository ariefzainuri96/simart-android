package simart.umby.android.pages.manajemen_aset.tambah_peminjaman_aset

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import simart.umby.android.model.manajemen_aset.DetailPeminjamanAsetForm
import simart.umby.android.model.manajemen_aset.PeminjamanAsetForm
import javax.inject.Inject

@HiltViewModel
class TambahPeminjamanVM @Inject constructor(
    val app: Application
) : ViewModel() {
    var form = MutableStateFlow(PeminjamanAsetForm()); private set
    var detailPeminjamanForm = MutableStateFlow(DetailPeminjamanAsetForm()); private set
    var tipePeminjamList = MutableStateFlow(listOf<String>()); private set
    var identitasPeminjamList = MutableStateFlow(listOf<String>()); private set
    var fakultasList = MutableStateFlow(listOf<String>()); private set
    var satuanList = MutableStateFlow(listOf<String>()); private set

    fun updateForm(update: PeminjamanAsetForm.() -> PeminjamanAsetForm) {
        form.update {
            it.update()
        }
    }

    fun updateDetailPeminjamanForm(update: DetailPeminjamanAsetForm.() -> DetailPeminjamanAsetForm) {
        detailPeminjamanForm.update {
            it.update()
        }
    }

    fun getTipePeminjam() {
        viewModelScope.launch {
            delay(1000L)

            tipePeminjamList.value = listOf("Mahasiswa", "Dosen", "Admin")
        }
    }

    fun getIdentitasPeminjam() {
        viewModelScope.launch {
            delay(1000L)

            identitasPeminjamList.value = listOf("KTP", "SIM", "Kartu Mahasiswa")
        }
    }

    fun getFakultas() {
        viewModelScope.launch {
            delay(1000L)

            fakultasList.value = listOf("Kedokteran", "Teknik")
        }
    }

    fun getSatuan() {
        viewModelScope.launch {
            delay(1000L)

            satuanList.value = listOf("Unit")
        }
    }

    init {
        getTipePeminjam()
        getIdentitasPeminjam()
        getFakultas()
        getSatuan()
    }
}