package simart.umby.android.pages.task_approval.section.detail_peminjaman_aset

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import simart.umby.android.R
import simart.umby.android.component.compose.CustomBSHeader
import simart.umby.android.component.compose.CustomTitleContent
import simart.umby.android.component.compose.ExpandableCard
import simart.umby.android.component.compose.theme.SfPro400

@Composable
fun DetailPeminjamanAsetBS(modifier: Modifier = Modifier, onCloseClick: (CoroutineScope) -> Unit) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.Start, modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.white))
    ) {
        CustomBSHeader(title = "Testing", onCloseClick = { onCloseClick(scope) })

        Column(
            horizontalAlignment = Alignment.Start, modifier = Modifier
                .weight(1f).verticalScroll(scrollState)
        ) {
            Column(modifier = Modifier.padding(all = 16.dp)) {
                CustomTitleContent("No. Peminjaman", "123123123")

                Spacer(Modifier.height(8.dp))

                CustomTitleContent("Tipe Peminjam", "Mahasiswa")

                Spacer(Modifier.height(8.dp))

                CustomTitleContent("Identitas Peminjam", "KTP")

                Spacer(Modifier.height(8.dp))

                CustomTitleContent("Nama Peminjam", "Arief Zainuri")

                Spacer(Modifier.height(8.dp))

                CustomTitleContent(
                    "Alamat Peminjam",
                    "Jl. Jend. Sudirman, Gowongan, Kec. Jetis, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55233"
                )

                Spacer(Modifier.height(8.dp))

                CustomTitleContent("Fakultas", "Sistem Informasi")

                Spacer(Modifier.height(8.dp))

                CustomTitleContent("No. HP / Telp", "08128712")

                Spacer(Modifier.height(8.dp))

                CustomTitleContent("Tanggal Pinjam", "06/08/2024")

                Spacer(Modifier.height(8.dp))

                CustomTitleContent("Tanggal Kembali", "06/10/2024")

                Spacer(Modifier.height(8.dp))
            }

            HorizontalDivider(
                color = colorResource(R.color.lineSeparator2), thickness = 4.dp
            )

            ExpandableCard(modifier = Modifier
                .padding(top = 16.dp)
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
                    listOf<String>("Detail Peminjaman 1 Lorem ipsum dolor sit amet asdkajsdkjasd " +
                            "consectuer",
                        "Detail Peminjaman 2").forEach {
                        item ->
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFE3EAFF))
                                .padding(16.dp)
                        ) {
                            Text(
                                item, style = SfPro400.copy(colorResource(R.color.grey1)),
                                modifier = Modifier.weight(1f)
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                "1 Unit", style = SfPro400.copy(
                                    colorResource(R.color.blue4), 12.sp
                                )
                            )
                        }
                        HorizontalDivider(color = colorResource(R.color.transparent), thickness = 8.dp)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}