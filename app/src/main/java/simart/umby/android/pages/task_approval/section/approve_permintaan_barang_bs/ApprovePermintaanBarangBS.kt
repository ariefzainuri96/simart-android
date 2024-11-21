package simart.umby.android.pages.task_approval.section.approve_permintaan_barang_bs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import simart.umby.android.R
import simart.umby.android.component.compose.ButtonType
import simart.umby.android.component.compose.CustomButton
import simart.umby.android.component.compose.CustomTextField
import simart.umby.android.component.compose.theme.SfPro500

@Composable
fun ApprovePermintaanBarangBS(
    modifier: Modifier = Modifier,
    onCloseClick: (CoroutineScope) -> Unit
) {
    val scope = rememberCoroutineScope()
    val viewModel = viewModel<ApprovePermintaanBarangBSVM>()
    val keterangan = viewModel.form.collectAsState()

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(
                colorResource(R.color.white),
                RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Approve Permintaan Barang",
                style = SfPro500.copy(fontSize = 16.sp),
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

        Column(modifier = Modifier.padding(16.dp)) {
            CustomTextField(
                value = keterangan.value,
                onValueChange = {
                    viewModel.updateForm(it)
                },
                maxLines = 3,
                minLines = 3,
                singleLine = false,
                label = "Keterangan",
                placeholder = "Masukkan keterangan",
                keyboardType = KeyboardType.Text,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                CustomButton(
                    buttonType = ButtonType.Outlined,
                    value = "TOLAK",
                    borderColor = colorResource(R.color.red2),
                    modifier = Modifier.weight(1f),
                ) {
                    println("tolak")
                }

                Spacer(modifier = Modifier.width(8.dp))

                CustomButton(
                    buttonType = ButtonType.Primary,
                    value = "TERIMA",
                    backgroundColor = colorResource(R.color.green4),
                    modifier = Modifier.weight(1f),
                ) {
                    println("terima")
                }
            }
        }
    }
}

@Preview
@Composable
fun ApprovePermintaanBarangBSPreview() {
    ApprovePermintaanBarangBS {

    }
}