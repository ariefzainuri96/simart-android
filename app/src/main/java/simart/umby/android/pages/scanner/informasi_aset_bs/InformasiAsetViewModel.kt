package simart.umby.android.pages.scanner.informasi_aset_bs

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InformasiAsetViewModel: ViewModel() {
    private var _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    fun addCounter() {
        _counter.value += 1
    }
}