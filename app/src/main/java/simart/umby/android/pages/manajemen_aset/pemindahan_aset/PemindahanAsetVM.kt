package simart.umby.android.pages.manajemen_aset.pemindahan_aset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import simart.umby.android.data.repository.ManajemenAsetRepository
import simart.umby.android.data.response.PemindahanAsetData
import simart.umby.android.utils.RequestState
import javax.inject.Inject

@HiltViewModel
class PemindahanAsetVM @Inject constructor(
    private val repository: ManajemenAsetRepository
): ViewModel() {
    var pemindahanAset = MutableStateFlow(listOf<PemindahanAsetData>()); private set
    var pemindahanAsetState = MutableStateFlow(RequestState.IDLE); private set

    fun getPemindahanAset() {
        val handler = CoroutineExceptionHandler {_, throwable ->
            println("Error happening: $throwable")

            pemindahanAsetState.value = RequestState.ERROR
        }

        pemindahanAsetState.value = RequestState.LOADING

        viewModelScope.launch(handler) {
            delay(1000L)

            val data = async { repository.getPemindahanAset() }.await()

            data.body()?.let {
                pemindahanAset.value = it.data ?: listOf()
            }

            pemindahanAsetState.value = if (data.isSuccessful) RequestState.SUCCESS else RequestState.ERROR
        }
    }

    init {
        getPemindahanAset()
    }
}