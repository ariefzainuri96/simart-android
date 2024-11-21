package simart.umby.android.pages.task_approval.section.detail_peminjaman_aset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import simart.umby.android.R
import simart.umby.android.component.compose.CustomTitleContent

@Composable
fun DetailPeminjamanAsetBS(modifier: Modifier = Modifier, onCloseClick: (CoroutineScope) -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.Start, modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        CustomTitleContent("No. Peminjaman", "123123123")
        Spacer(Modifier.height(8.dp))
        CustomTitleContent("Tipe Peminjam", "Mahasiswa")
        Spacer(Modifier.height(8.dp))
        CustomTitleContent("Detail Peminjaman Aset", "Detail Peminjaman Aset")
    }
}