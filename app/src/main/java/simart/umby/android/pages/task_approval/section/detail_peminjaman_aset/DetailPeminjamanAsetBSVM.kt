package simart.umby.android.pages.task_approval.section.detail_peminjaman_aset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import simart.umby.android.data.repository.TaskApprovalRepository
import simart.umby.android.model.TaskApprovalModel
import simart.umby.android.utils.RequestState
import javax.inject.Inject

@HiltViewModel
class DetailPeminjamanAsetBSVM @Inject constructor(
    private val repository: TaskApprovalRepository
) : ViewModel() {
    var state = MutableStateFlow(RequestState.IDLE); private set
    var detailData = MutableStateFlow(TaskApprovalModel()); private set

    fun getDetailData() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            println("Error happening: $throwable")

            state.value = RequestState.ERROR
        }

        viewModelScope.launch(handler) {
            state.value = RequestState.LOADING

            val data = async { repository.getDetailPeminjamanAset() }.await()

            state.value = if (data == null) RequestState.ERROR else RequestState.SUCCESS

            detailData.value = data ?: TaskApprovalModel()
        }
    }

    fun reset() {
        println("Reset Data")
        state.value = RequestState.IDLE
        detailData.value = TaskApprovalModel()
    }

    init {
        println("init DetailPeminjamanAsetBSVM")
    }
}