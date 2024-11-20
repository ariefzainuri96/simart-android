package simart.umby.android.pages.task_approval.component.approve_permintaan_barang_bs

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import simart.umby.android.utils.RequestState
import javax.inject.Inject

@HiltViewModel
class ApprovePermintaanBarangBSVM @Inject constructor(
    application: Application
) : ViewModel() {
    private var _state = MutableStateFlow(RequestState.IDLE)
    var state = _state.asStateFlow()

    private var _form = MutableStateFlow("")
    var form = _form.asStateFlow()

    fun updateForm(value: String) {
        _form.value = value
    }

    fun decline() {
        _state.value = RequestState.LOADING

        viewModelScope.launch {
            delay(1000L)

            _state.value = RequestState.SUCCESS
        }
    }

    fun approve() {
        _state.value = RequestState.LOADING

        viewModelScope.launch {
            delay(1000L)

            _state.value = RequestState.SUCCESS
        }
    }

    fun resetState() {
        _form.value = ""
        _state.value = RequestState.IDLE
    }
}