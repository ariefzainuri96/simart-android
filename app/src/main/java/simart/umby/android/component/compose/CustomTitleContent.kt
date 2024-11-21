package simart.umby.android.component.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro400

@Composable
fun CustomTitleContent(title: String, content: String, modifier: Modifier = Modifier) {
    Column {
        Text(title, style = SfPro400.copy(fontSize = 12.sp, color = colorResource(R.color.grey3)))
        Spacer(modifier = Modifier.height(4.dp))
        Text(content, style = SfPro400)
    }
}