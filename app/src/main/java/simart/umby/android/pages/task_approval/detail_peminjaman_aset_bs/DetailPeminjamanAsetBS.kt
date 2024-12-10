package simart.umby.android.pages.task_approval.detail_peminjaman_aset_bs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import simart.umby.android.R
import simart.umby.android.component.compose.Center
import simart.umby.android.component.compose.CustomBSHeader
import simart.umby.android.component.compose.CustomTitleContent
import simart.umby.android.component.compose.ExpandableCard
import simart.umby.android.component.compose.theme.SfPro400
import simart.umby.android.component.page.task_approval.DetailPeminjaman
import simart.umby.android.component.page.task_approval.HistoryPersetujuan
import simart.umby.android.model.TaskApprovalModel
import simart.umby.android.pages.task_approval.LocalDetailPeminjamanAsetBSVM
import simart.umby.android.utils.RequestState

@Composable
fun DetailPeminjamanAsetBS(modifier: Modifier = Modifier, onCloseClick: (CoroutineScope) -> Unit) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val viewModel = LocalDetailPeminjamanAsetBSVM.current
    val detailData = viewModel.detailData.collectAsState()
    val state = viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.Start, modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
    ) {
        CustomBSHeader(title = "Detail Peminjaman Aset", onCloseClick = { onCloseClick(scope) })

        if (state.value == RequestState.LOADING) {
            Center {
                CircularProgressIndicator(
                    modifier = Modifier.width(24.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        } else {
            DetailPeminjamanAsetBSContent(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState),
                detailData = detailData.value
            )
        }

    }
}

@Composable
fun DetailPeminjamanAsetBSContent(modifier: Modifier, detailData: TaskApprovalModel) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(all = 16.dp)) {
            CustomTitleContent("No. Peminjaman", detailData.noPeminjamanAset)

            Spacer(Modifier.height(8.dp))

            CustomTitleContent("Tipe Peminjam", detailData.tipePeminjam)

            Spacer(Modifier.height(8.dp))

            CustomTitleContent("Identitas Peminjam", detailData.identitasPeminjam)

            Spacer(Modifier.height(8.dp))

            CustomTitleContent("Nama Peminjam", detailData.namaPeminjam)

            Spacer(Modifier.height(8.dp))

            CustomTitleContent(
                "Alamat Peminjam",
                detailData.alamatPeminjam
            )

            Spacer(Modifier.height(8.dp))

            CustomTitleContent("Fakultas", detailData.fakultas)

            Spacer(Modifier.height(8.dp))

            CustomTitleContent("No. HP / Telp", detailData.noHP)

            Spacer(Modifier.height(8.dp))

            CustomTitleContent("Tanggal Pinjam", detailData.tanggalPinjam)

            Spacer(Modifier.height(8.dp))

            CustomTitleContent("Tanggal Kembali", detailData.tanggalKembali)

            Spacer(Modifier.height(8.dp))
        }

        HorizontalDivider(
            color = colorResource(R.color.lineSeparator2), thickness = 4.dp
        )

        Spacer(Modifier.height(16.dp))

        ExpandableCard(modifier = Modifier
            .padding(horizontal = 16.dp),
            header = {
                Text(
                    "Detail Peminjaman", style = SfPro400.copy(
                        fontSize = 12.sp, color =
                        colorResource(R.color.primary3)
                    )
                )
            }) {
            Column {
                detailData.detailPeminjaman.forEach { item ->
                    DetailPeminjaman(item = item)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        HorizontalDivider(
            color = colorResource(R.color.lineSeparator2), thickness = 4.dp
        )

        Spacer(Modifier.height(16.dp))

        ExpandableCard(modifier = Modifier
            .padding(horizontal = 16.dp),
            header = {
                Text(
                    "History Persetujuan", style = SfPro400.copy(
                        fontSize = 12.sp, color =
                        colorResource(R.color.primary3)
                    )
                )
            }) {
            Column {
                detailData.historyPersetujuan.forEach { item ->
                    HistoryPersetujuan(item = item)
                }
            }
        }

        Spacer(Modifier.height(16.dp))
    }
}