package simart.umby.android.pages.task_approval

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.R
import simart.umby.android.component.compose.Center
import simart.umby.android.component.compose.CustomTopbar
import simart.umby.android.component.compose.theme.SimartUmbyTheme
import simart.umby.android.pages.task_approval.component.TaskApprovalItem
import simart.umby.android.pages.task_approval.section.approve_permintaan_barang_bs.ApprovePermintaanBarangBSVM
import simart.umby.android.pages.task_approval.section.detail_peminjaman_aset.DetailPeminjamanAsetBSVM
import simart.umby.android.utils.RequestState

val LocalDetailPeminjamanAsetBSVM = compositionLocalOf<DetailPeminjamanAsetBSVM> {
    error("DetailPeminjamanAsetBSVM not provided")
}
val LocalApprovePermintaanBarangBSVM = compositionLocalOf<ApprovePermintaanBarangBSVM> {
    error("ApprovePermintaanBarangBSVM not provided")
}

@AndroidEntryPoint
class TaskApprovalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(statusBarStyle = SystemBarStyle.dark(getColor(R.color.defaultStatusBar)))

        setContent {
            SimartUmbyTheme {
                TaskApprovalContent(context = this)
            }
        }
    }
}

@Composable
fun TaskApprovalContent(modifier: Modifier = Modifier, context: ComponentActivity) {
    val detailPeminjamanAsetBSVM = viewModel<DetailPeminjamanAsetBSVM>()
    val approvePermintaanBarangBSVM = viewModel<ApprovePermintaanBarangBSVM>()
    val viewModel = viewModel<TaskApprovalVM>()

    val tasks = viewModel.taskApprovals.collectAsState()
    val state = viewModel.taskApprovalsState.collectAsState()

//    Utils.Companion.setStatusBarShown(context, LocalView.current)

    Scaffold(
        modifier = modifier.fillMaxSize().background(colorResource(R.color.white)),
        topBar = {
            CustomTopbar(title = "Task Approval (23)", onBackClick = {
                context.finish()
            }) {
                Icon(
                    painterResource(R.drawable.ic_filter),
                    tint = Color.White,
                    modifier = Modifier.size(20.dp),
                    contentDescription = null
                )
            }
        }) { innerPadding ->
        if (state.value == RequestState.LOADING) {
            Center {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                )
            }
        } else {
            CompositionLocalProvider(
                LocalDetailPeminjamanAsetBSVM provides
                        detailPeminjamanAsetBSVM
            ) {
                CompositionLocalProvider(LocalApprovePermintaanBarangBSVM provides
                        approvePermintaanBarangBSVM) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        itemsIndexed(tasks.value) { index, task ->
                            TaskApprovalItem(
                                task,
                                modifier = Modifier.padding(top = if (index == 0) 16.dp else 0.dp)
                            )
                            HorizontalDivider(
                                color = colorResource(R.color.transparent),
                                thickness = 10.dp
                            )
                        }
                    }
                }
            }
        }
    }
}