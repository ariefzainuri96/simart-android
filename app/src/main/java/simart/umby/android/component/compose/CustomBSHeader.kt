package simart.umby.android.component.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro500

@Composable
fun CustomBSHeader(
    modifier: Modifier = Modifier, title: String, onCloseClick: () -> Unit,
    action: @Composable () -> Unit = {}
) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()

    Box {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(colorResource(R.color.primary))
                .height(64.dp + paddingValues.calculateTopPadding())
                .padding(horizontal = 16.dp)
                .padding(bottom = paddingValues.calculateTopPadding())
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                painterResource(R.drawable.ic_close),
                contentDescription =
                null,
                tint = colorResource(R.color.white),
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
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

        Box(modifier = Modifier.height(paddingValues.calculateTopPadding()).fillMaxWidth()
            .background(color = colorResource(R.color.defaultStatusBar)))
    }
}