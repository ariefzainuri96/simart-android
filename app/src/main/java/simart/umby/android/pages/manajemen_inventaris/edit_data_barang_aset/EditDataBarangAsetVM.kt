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

    // availability
    var ciaState = MutableStateFlow(RequestState.IDLE); private set
    var ciaList = MutableStateFlow<List<String>>(emptyList()); private set

    // vendor
    private var vendorMaxPage = 3
    private var vendorPage = 1
    var vendorState = MutableStateFlow(RequestState.IDLE); private set
    var vendorList = MutableStateFlow<List<String>>(emptyList()); private set

    // kategori aset
    private var kategoriAsetMaxPage = 3
    private var kategoriAsetPage = 1
    var kategoriAsetState = MutableStateFlow(RequestState.IDLE); private set
    var kategoriAsetList = MutableStateFlow<List<String>>(emptyList()); private set

    // sub kategori aset
    private var subKategoriAsetMaxPage = 3
    private var subKategoriAsetPage = 1
    var subKategoriAsetState = MutableStateFlow(RequestState.IDLE); private set
    var subKategoriAsetList = MutableStateFlow<List<String>>(emptyList()); private set

    // location
    private var locationMaxPage = 3
    private var locationPage = 1
    var locationState = MutableStateFlow(RequestState.IDLE); private set
    var locationList = MutableStateFlow<List<String>>(emptyList()); private set

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
        if (ciaState.value == RequestState.LOADING) {
            println("didnt continue getAvailability due to status loading, max page, or loading next page")
            return
        }

        ciaState.value = RequestState.LOADING

        viewModelScope.launch {
            delay(1500L)

            var newData = mutableListOf<String>()
            for (i in 1..10) {
                newData.add("Data - ${i + ciaList.value.count()}")
            }

            ciaState.value = RequestState.SUCCESS
            ciaList.value = newData
        }
    }

    fun getVendor(isInitial: Boolean = true) {
        if (vendorState.value == RequestState.LOADING || vendorState.value ==
            RequestState.MAX_PAGE || vendorState.value == RequestState.LOADING_NEXT_PAGE
        ) {
            println("didnt continue getVendor due to status loading, max page, or loading next page")
            return
        }

        if (isInitial) {
            println("isInitial")
            vendorList.value = emptyList()
            vendorPage = 1
            vendorState.value = RequestState.IDLE
        }

        println("getVendor -> $isInitial")

        vendorState.value = if (isInitial) RequestState.LOADING else RequestState.LOADING_NEXT_PAGE

        viewModelScope.launch {
            delay(1500L)

            var newData = mutableListOf<String>()
            for (i in 1..10) {
                newData.add("Data - ${i + vendorList.value.count()}")
            }

            vendorList.value = vendorList.value + newData

            vendorState.value = RequestState.SUCCESS

            if (vendorPage == vendorMaxPage) {
                vendorState.value = RequestState.MAX_PAGE
            }

            vendorPage += 1
        }
    }

    fun getKategoriAset(isInitial: Boolean = true) {
        if (kategoriAsetState.value == RequestState.LOADING || kategoriAsetState.value ==
            RequestState.MAX_PAGE || kategoriAsetState.value == RequestState.LOADING_NEXT_PAGE
        ) {
            println("didnt continue getKategoriAset due to status loading, max page, or loading next page")
            return
        }
        if (isInitial) {
            kategoriAsetList.value = emptyList()
            kategoriAsetPage = 1
            kategoriAsetState.value = RequestState.IDLE
        }

        kategoriAsetState.value = if (isInitial) RequestState.LOADING else RequestState
            .LOADING_NEXT_PAGE

        viewModelScope.launch {
            delay(1500L)

            var newData = mutableListOf<String>()
            for (i in 1..10) {
                newData.add("Data - ${i + kategoriAsetList.value.count()}")
            }

            kategoriAsetState.value = RequestState.SUCCESS

            if (kategoriAsetPage == kategoriAsetMaxPage) {
                kategoriAsetState.value = RequestState.MAX_PAGE
            }

            kategoriAsetPage += 1
            kategoriAsetList.value = kategoriAsetList.value + newData
        }
    }

    fun getSubKategoriAset(isInitial: Boolean = true) {
        if (subKategoriAsetState.value == RequestState.LOADING || subKategoriAsetState.value ==
            RequestState.MAX_PAGE || subKategoriAsetState.value == RequestState.LOADING_NEXT_PAGE
        ) {
            println("didnt continue getSubKategoriAset due to status loading, max page, or loading next page")
            return
        }
        if (isInitial) {
            subKategoriAsetList.value = emptyList()
            subKategoriAsetPage = 1
            subKategoriAsetState.value = RequestState.IDLE
        }

        subKategoriAsetState.value = if (isInitial) RequestState.LOADING else RequestState
            .LOADING_NEXT_PAGE

        viewModelScope.launch {
            delay(1500L)

            var newData = mutableListOf<String>()
            for (i in 1..10) {
                newData.add("Data - ${i + subKategoriAsetList.value.count()}")
            }

            subKategoriAsetState.value = RequestState.SUCCESS

            if (subKategoriAsetPage == subKategoriAsetMaxPage) {
                subKategoriAsetState.value = RequestState.MAX_PAGE
            }

            subKategoriAsetPage += 1
            subKategoriAsetList.value = subKategoriAsetList.value + newData
        }
    }

    fun getLocation(isInitial: Boolean = true) {
        if (locationState.value == RequestState.LOADING || locationState.value ==
            RequestState.MAX_PAGE || locationState.value == RequestState.LOADING_NEXT_PAGE
        ) {
            println("didnt continue getLocation due to status loading, max page, or loading next page")
            return
        }
        if (isInitial) {
            locationList.value = emptyList()
            locationPage = 1
            locationState.value = RequestState.IDLE
        }

        locationState.value = if (isInitial) RequestState.LOADING else RequestState
            .LOADING_NEXT_PAGE

        viewModelScope.launch {
            delay(1500L)

            var newData = mutableListOf<String>()
            for (i in 1..10) {
                newData.add("Data - ${i + locationList.value.count()}")
            }

            locationState.value = RequestState.SUCCESS

            if (locationPage == locationMaxPage) {
                locationState.value = RequestState.MAX_PAGE
            }

            locationPage += 1
            locationList.value = locationList.value + newData
        }
    }
    
    init {
        getAvailability()
        getVendor()
        getKategoriAset()
        getSubKategoriAset()
        getLocation()
        getAvailability()
    }
}