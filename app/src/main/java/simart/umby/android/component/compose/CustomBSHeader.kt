package simart.umby.android.component.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro500

@Composable
fun CustomBSHeader(
    modifier: Modifier = Modifier, title: String, onCloseClick: () -> Unit, action: @Composable ()
    -> Unit =
        {}
) {
    Row(
        modifier = modifier
            .background(colorResource(R.color.primary))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Icon(
            painterResource(R.drawable.ic_close),
            contentDescription =
            null, tint = colorResource(R.color.white), modifier = Modifier.size(20.dp).clickable {
                onCloseClick()
            }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            title,
            style = SfPro500.copy(fontSize = 18.sp, color = colorResource(R.color.white)),
            modifier =
            Modifier.weight
                (1f)
        )

        action()
    }
}