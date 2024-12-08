package simart.umby.android.pages.task_approval.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro400
import simart.umby.android.model.DetailPeminjamanAsetModel

@Composable
fun DetailPeminjaman(modifier: Modifier = Modifier, item: DetailPeminjamanAsetModel) {
    Row(
        modifier = modifier
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(6.dp), spotColor =
            colorResource(R.color.black).copy(alpha = 0.15f))
            .background(Color(0xFFE3EAFF))
            .padding(16.dp)
    ) {
        Text(
            item.namaAset, style = SfPro400.copy(colorResource(R.color.grey1)),
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            "${item.jumlahAset} Unit", style = SfPro400.copy(
                colorResource(R.color.blue4), 12.sp
            )
        )
    }
    HorizontalDivider(color = colorResource(R.color.transparent), thickness = 8.dp)
}