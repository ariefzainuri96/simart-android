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
) : ViewModel() {
    private var _editForm = MutableStateFlow(DataBarangAsetModel())
    val editForm = _editForm.asStateFlow()

    private var _state = MutableStateFlow(RequestState.IDLE)
    val state = _state.asStateFlow()

    private var _availabilityMaxPage = 3
    private var _availabilityPage = 1
    private var _availabilityState = MutableStateFlow(RequestState.IDLE)
    val availabilityState = _availabilityState.asStateFlow()
    private var _availabilityList = MutableStateFlow<List<String>>(emptyList())
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

    fun getAvailability(isInitial: Boolean = true) {
        if (_availabilityState.value == RequestState.LOADING || _availabilityState.value ==
            RequestState.MAX_PAGE || _availabilityState.value == RequestState.LOADING_NEXT_PAGE
        ) {
            println("didnt continue getAvailability due to status loading, max page, or loading next page")
            return
        }

        if (isInitial) {
            println("isInitial")
            _availabilityList.value = emptyList()
            _availabilityPage = 1
            _availabilityState.value = RequestState.IDLE
        }

        println("getAvailability -> $isInitial")

        _availabilityState.value = if (isInitial) RequestState.LOADING else RequestState
            .LOADING_NEXT_PAGE

        viewModelScope.launch {
            delay(1500L)

            var newData = mutableListOf<String>()
            for (i in 1 ..10) {
                newData.add("Data - ${i + _availabilityList.value.count()}")
            }

            _availabilityList.value = _availabilityList.value + newData

            _availabilityState.value = RequestState.SUCCESS

            if (_availabilityPage == _availabilityMaxPage) {
                _availabilityState.value = RequestState.MAX_PAGE
            }

            _availabilityPage += 1
        }
    }

    init {
        getAvailability()
    }
}