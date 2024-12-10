package simart.umby.android.component.page.task_approval

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.component.compose.ExpandableCard
import simart.umby.android.component.compose.theme.SfPro300
import simart.umby.android.component.compose.theme.SfPro400
import simart.umby.android.model.HistoryPersetujuanModel

@Composable
fun HistoryPersetujuan(modifier: Modifier = Modifier, item: HistoryPersetujuanModel) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(6.dp),
                spotColor = colorResource(R.color.black).copy(alpha = 0.15f)
            )
            .background(colorResource(R.color.blue5))
            .padding(16.dp)
    ) {
        Row {
            Text(item.disetujuiOleh, style = SfPro400, modifier = Modifier.weight(1f))
            Spacer(Modifier.width(10.dp))
            Text(item.status, style = SfPro400.copy(colorResource(R.color.green3), 12.sp))
        }

        Spacer(Modifier.height(12.dp))

        Text(item.tanggal, style = SfPro400.copy(colorResource(R.color.grey3), 12.sp))

        Spacer(Modifier.height(8.dp))

        HorizontalDivider(color = colorResource(R.color.lineSeparator2), thickness = 1.dp)

        Spacer(Modifier.height(8.dp))

        ExpandableCard(header = {
            Text("Keterangan", style = SfPro400.copy(colorResource(R.color.textPrimary), 12.sp))
        }) {
            Text(item.keterangan, style = SfPro300.copy(colorResource(R.color.grey3)))
        }
    }
}