package simart.umby.android.pages.task_approval.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro400
import simart.umby.android.component.compose.theme.SfPro500
import simart.umby.android.component.compose.theme.SfPro700
import simart.umby.android.model.TaskApprovalModel
import simart.umby.android.pages.task_approval.component.approve_permintaan_barang_bs.ApprovePermintaanBarangBS
import simart.umby.android.pages.task_approval.component.approve_permintaan_barang_bs.ApprovePermintaanBarangBSVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApprovalItem(data: TaskApprovalModel, modifier: Modifier = Modifier) {
    val viewModel = viewModel<ApprovePermintaanBarangBSVM>()
    val sheetState = rememberModalBottomSheetState(
//        uncomment this line to prevent bottom sheet from collapsing by swipe or click outside
//        skipPartiallyExpanded = true,
//        confirmValueChange = { newValue ->
//            newValue != SheetValue.Hidden
//        }
    )
    var expanded by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(sheetState.currentValue) {
        if (sheetState.currentValue == SheetValue.Hidden) {
            println("BottomSheet Hidden")
            viewModel.resetState()
        }
    }

    Surface(
        shape = RoundedCornerShape(6.dp),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.white), shape = RoundedCornerShape(6.dp))
                .padding(16.dp)
        ) {
            Row {
                Text(
                    data.title,
                    style = SfPro500,
                    modifier = Modifier.weight(1F)
                )

                Spacer(Modifier.width(8.dp))

                Box {
                    Icon(
                        painterResource(R.drawable.ic_more_vertical),
                        contentDescription = null,
                        tint = colorResource(R.color.grey1),
                        modifier = Modifier.size(20.dp).clickable {
                            expanded = !expanded
                        },
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Row {
                                Icon(
                                    painterResource(R.drawable.ic_eye),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )

                                Spacer(Modifier.width(8.dp))

                                Text("Detail", style = SfPro400)
                            } },
                            onClick = { /* Handle refresh! */ }
                        )

                        HorizontalDivider()

                        DropdownMenuItem(
                            text = { Row {
                                Icon(
                                    painterResource(R.drawable.ic_approve),
                                    contentDescription = null,
                                    tint = colorResource(R.color.blue4),
                                    modifier = Modifier.size(16.dp),
                                )

                                Spacer(Modifier.width(8.dp))

                                Text("Detail", style = SfPro400.copy(colorResource(R.color.blue4)))
                            } },
                            onClick = { /* Handle settings! */ }
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Row {
                Text(data.namaAset, style = SfPro500.copy(colorResource(R.color.grey1), 12.sp), modifier = Modifier.weight(1F))
                Spacer(Modifier.width(10.dp))
                Text(data.noPeminjamanAset, style = SfPro400.copy(colorResource(R.color.textPrimary), 12.sp))
            }

            Spacer(Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(data.date, modifier = Modifier.weight(1F), style = SfPro400.copy(colorResource(R.color.blue4), 12.sp))
                Text("Status", style = SfPro400.copy(colorResource(R.color.purple3), 12.sp))
            }

            Spacer(Modifier.height(8.dp))

            HorizontalDivider(thickness = 1.dp, color = colorResource(R.color.lineSeparator))

            Spacer(Modifier.height(8.dp))

            TextButton({
                showBottomSheet = true
            },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                .border(border = BorderStroke(1.dp, colorResource(R.color.blue4)), shape = RoundedCornerShape(4.dp))
                .fillMaxWidth()
                .height(41.dp)
            ) {
                Text("APPROVE", style = SfPro700.copy(color = colorResource(R.color.blue4)))
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        // leave this empty to prevent dismiss by swipe or clicking outside
                        showBottomSheet = false
                    },
                    dragHandle = {
                        // leave this empty to hide bottomsheet header
                    },
                ) {
                    ApprovePermintaanBarangBS { scope ->
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TaskApprovalItemPreview() {
    TaskApprovalItem(TaskApprovalModel("Peminjaman Aset", "Nama Aset", "1212", "Date", "Status"))
}