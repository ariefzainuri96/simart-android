package simart.umby.android.pages.task_approval.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro400
import simart.umby.android.component.compose.theme.SfPro500
import simart.umby.android.component.compose.theme.SfPro700
import simart.umby.android.model.TaskApprovalModel

@Composable
fun TaskApprovalItem(data: TaskApprovalModel, modifier: Modifier = Modifier) {
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
            Text(
                data.title,
                style = SfPro500,
            )

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
                println("APPROVE CLICKED!")
            },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                .border(border = BorderStroke(1.dp, colorResource(R.color.blue4)), shape = RoundedCornerShape(4.dp))
                .fillMaxWidth()
                .height(41.dp)
            ) {
                Text("APPROVE", style = SfPro700.copy(color = colorResource(R.color.blue4)))
            }
        }
    }
}