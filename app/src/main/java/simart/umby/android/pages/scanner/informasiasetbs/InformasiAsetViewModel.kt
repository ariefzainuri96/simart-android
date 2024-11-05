package simart.umby.android.pages.scanner.informasiasetbs

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