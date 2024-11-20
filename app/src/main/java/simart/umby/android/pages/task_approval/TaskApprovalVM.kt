package simart.umby.android.pages.task_approval

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import simart.umby.android.model.TaskApprovalModel
import simart.umby.android.utils.RequestState
import javax.inject.Inject

@HiltViewModel
class TaskApprovalVM @Inject constructor(
    private val app: Application
) : ViewModel() {
    private var _taskApprovals = MutableStateFlow(listOf<TaskApprovalModel>())
    var taskApprovals = _taskApprovals.asStateFlow()

    var taskApprovalsState = MutableStateFlow(RequestState.IDLE)
        private set

    fun getTaskApprovals() {
        viewModelScope.launch {
            taskApprovalsState.value = RequestState.LOADING

            delay(1000L)

            _taskApprovals.value = listOf<TaskApprovalModel>(
                TaskApprovalModel(
                    "Peminjaman Aset1",
                    "Nama Aset1",
                    "12345",
                    "21/08/2024",
                    "Status"
                ),
                TaskApprovalModel(
                    "Peminjaman Aset1",
                    "Nama Aset1",
                    "12345",
                    "21/08/2024",
                    "Status"
                ),
            )

            taskApprovalsState.value = RequestState.SUCCESS
        }
    }

    init {
        getTaskApprovals()
    }
}