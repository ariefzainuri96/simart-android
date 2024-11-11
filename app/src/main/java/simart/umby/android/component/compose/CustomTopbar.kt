package simart.umby.android.component.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.utils.Utils

@Composable
fun CustomTopbar(title: String, onBackClick: () -> Unit, action: @Composable RowScope.() -> Unit = {}) {
    val paddingValues = WindowInsets.systemBars.asPaddingValues()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.primary))
            .height(64.dp + paddingValues.calculateTopPadding())
            .windowInsetsPadding(WindowInsets.systemBars)
        ,
        ) {
        Spacer(Modifier.width(16.dp))
        Icon(painter = painterResource(R.drawable.ic_back), modifier = Modifier.size(20.dp).clickable {
            onBackClick()
        }, tint = Color.White, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text(text = title, fontSize = 18.sp, color = Color.White, fontFamily = Utils.FontName, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        action()
        Spacer(Modifier.width(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTopbarPreview() {
    CustomTopbar("Testing1", onBackClick = {})
}