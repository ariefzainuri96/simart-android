package simart.umby.android.pages.task_approval.component.approve_permintaan_barang_bs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import simart.umby.android.R

@Composable
fun ApprovePermintaanBarangBS(modifier: Modifier = Modifier, onCloseClick: (CoroutineScope) -> Unit) {
    val scope = rememberCoroutineScope()
    val viewModel = viewModel<ApprovePermintaanBarangBSVM>()
    val keterangan = viewModel.form.collectAsState()

    Column (
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(colorResource(R.color.white))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Approve Permintaan Barang",
                modifier = Modifier.weight(1F)
            )

            Spacer(Modifier.width(8.dp))

            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = null,
                tint = colorResource(R.color.red1),
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        onCloseClick(scope)
                    }
            )
        }

        HorizontalDivider(modifier = Modifier.background(colorResource(R.color.lineSeparator2)))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            TextField(
                value = keterangan.value,
                onValueChange = {
                    viewModel.updateForm(it)
                },
                colors = TextFieldDefaults.colors(
                    //setting the text field background when it is focused
                    focusedContainerColor = colorResource(R.color.white),
                    //setting the text field background when it is unfocused or initial state
                    unfocusedContainerColor = colorResource(R.color.white),
                    //setting the text field background when it is disabled
                    disabledContainerColor = colorResource(R.color.grey1),
                    focusedIndicatorColor = colorResource(R.color.transparent),
                    unfocusedIndicatorColor = colorResource(R.color.transparent),
                ),
                shape = RoundedCornerShape(8.dp),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .border(
                        width = 1.dp,
                        color = colorResource(R.color.lineSeparator2),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}

@Preview
@Composable
fun ApprovePermintaanBarangBSPreview() {
    ApprovePermintaanBarangBS {

    }
}