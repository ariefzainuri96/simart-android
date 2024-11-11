package simart.umby.android.pages.task_approval

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import simart.umby.android.R
import simart.umby.android.component.compose.CustomTopbar
import simart.umby.android.component.compose.TransparentStatusBar
import simart.umby.android.pages.task_approval.ui.theme.SimartUmbyTheme

class TaskApprovalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        setContent {
            SimartUmbyTheme {
                TransparentStatusBar()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CustomTopbar(title = "Task Approval (23)", onBackClick = {
                            finish()
                        }) {
                            Icon(painterResource(R.drawable.ic_filter), tint = Color.White, modifier = Modifier.size(20.dp), contentDescription = null)
                        }
                    }) { innerPadding ->
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(text = "asd", modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SimartUmbyTheme {
//
//    }
//}