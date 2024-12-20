package simart.umby.android.pages.manajemen_aset.tambah_peminjaman_aset

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import simart.umby.android.data.repository.ManajemenAsetRepository
import simart.umby.android.data.response.HistoryPersetujuanData
import simart.umby.android.model.FormErrorModel
import simart.umby.android.model.manajemen_aset.DetailPeminjamanAsetForm
import simart.umby.android.model.manajemen_aset.PeminjamanAsetForm
import simart.umby.android.utils.RequestState
import simart.umby.android.utils.Utils.Companion.checkAllProperties
import javax.inject.Inject
import kotlin.collections.listOf

@HiltViewModel
class TambahPeminjamanVM @Inject constructor(
    val app: Application,
    val manajemenAsetRepository: ManajemenAsetRepository
) : ViewModel() {
    var form = MutableStateFlow(PeminjamanAsetForm()); private set
    var formError = MutableStateFlow(listOf<FormErrorModel>()); private set
    var detailPeminjamanForm = MutableStateFlow(DetailPeminjamanAsetForm()); private set
    var detailPeminjamanFormError = MutableStateFlow(listOf<FormErrorModel>()); private set
    var tipePeminjamList = MutableStateFlow(listOf<String>()); private set
    var identitasPeminjamList = MutableStateFlow(listOf<String>()); private set
    var fakultasList = MutableStateFlow(listOf<String>()); private set
    var satuanList = MutableStateFlow(listOf<String>()); private set

    var historyPersetujuanState = MutableStateFlow(RequestState.IDLE); private set
    var historyPersetujuan = MutableStateFlow(listOf<HistoryPersetujuanData>()); private set

    fun checkFormValid(): Boolean {
        val errors = form.value.checkAllProperties()

        formError.value = errors

        return errors.isEmpty()
    }

    fun checkDetailPeminjamanFormValid(): Boolean {
        val errors = detailPeminjamanForm.value.checkAllProperties()

        detailPeminjamanFormError.value = errors

        return errors.isEmpty()
    }

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

    fun getHistoryPersetujuan() {
        val handler = CoroutineExceptionHandler {_, throwable ->
            println("Error happening: $throwable")

            historyPersetujuanState.value = RequestState.ERROR
        }

        historyPersetujuanState.value = RequestState.LOADING

        viewModelScope.launch(handler) {
            delay(1000L)

            val data = async { manajemenAsetRepository.getHistoryPersetujuan() }.await()

            historyPersetujuan.value = data.body()?.data ?: listOf()

            historyPersetujuanState.value = if (data.isSuccessful) RequestState.SUCCESS else RequestState.ERROR
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
        getHistoryPersetujuan()
    }
}