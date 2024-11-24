package simart.umby.android.pages.manajemen_inventaris.edit_data_barang_aset

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import simart.umby.android.pages.manajemen_inventaris.data_barang_aset.model.DataBarangAsetModel
import simart.umby.android.utils.RequestState
import javax.inject.Inject
import kotlin.collections.mutableListOf

@HiltViewModel
class EditDataBarangAsetVM @Inject constructor(
    application: Application
): ViewModel() {
    private var _editForm = MutableStateFlow(DataBarangAsetModel())
    val editForm = _editForm.asStateFlow()

    private var _state = MutableStateFlow(RequestState.IDLE)
    val state = _state.asStateFlow()

    private var _availabilityState = MutableStateFlow(RequestState.IDLE)
    val availabilityState = _availabilityState.asStateFlow()
    private var _availabilityList = MutableStateFlow(mutableListOf<String>())
    val availabilityList = _availabilityList.asStateFlow()

    fun updateForm(update: DataBarangAsetModel.() -> DataBarangAsetModel) {
        _editForm.update {
            it.update()
        }
    }

    fun initForm(data: DataBarangAsetModel) {
        _editForm.value = data
    }

    // write function to save data
    fun saveData() {
        println("data -> ${_editForm.value}")
        _state.value = RequestState.LOADING

        viewModelScope.launch {
            delay(1000L)

            _state.value = RequestState.SUCCESS
        }
    }

    fun getAvailability() {
        _availabilityState.value = RequestState.LOADING

        viewModelScope.launch {
            delay(1000L)

            _availabilityList.value = mutableListOf("ABC", "DEF", "GHI")

            _availabilityState.value = RequestState.SUCCESS
        }
    }

    init {
        getAvailability()
    }
}