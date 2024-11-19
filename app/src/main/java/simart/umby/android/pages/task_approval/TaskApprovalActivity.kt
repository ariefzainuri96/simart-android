package simart.umby.android.pages.task_approval

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import simart.umby.android.R
import simart.umby.android.component.compose.CustomTopbar
import simart.umby.android.component.compose.TransparentStatusBar
import simart.umby.android.component.compose.theme.SimartUmbyTheme
import simart.umby.android.model.TaskApprovalModel
import simart.umby.android.pages.task_approval.component.TaskApprovalItem

@AndroidEntryPoint
class TaskApprovalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SimartUmbyTheme {
                TransparentStatusBar()

                TaskApprovalContent(context = this)
            }
        }
    }
}

@Composable
fun TaskApprovalContent(modifier: Modifier = Modifier, context: ComponentActivity) {
    val viewModel = viewModel<TaskApprovalVM>()
    val tasks = viewModel.taskApprovals.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CustomTopbar(title = "Task Approval (23)", onBackClick = {
                context.finish()
            }) {
                Icon(painterResource(R.drawable.ic_filter), tint = Color.White, modifier = Modifier.size(20.dp), contentDescription = null)
            }
        }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp).padding(bottom = 16.dp)) {
            itemsIndexed(tasks.value) { index, task ->
                TaskApprovalItem(task, modifier = Modifier.padding(top = if (index == 0) 16.dp else 0.dp))
                HorizontalDivider(color = colorResource(R.color.transparent), thickness = 8.dp)
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    SimartUmbyTheme {
        TaskApprovalItem(TaskApprovalModel("Peminjaman Aset", "Nama Aset", "1212", "Date", "Status"))
    }
}