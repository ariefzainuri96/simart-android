package simart.umby.android.component.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable fun TransparentStatusBar() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false // Set this to false for white icons
    val color = Color(0x80000000) // 50% dark transparency

    systemUiController.setSystemBarsColor( color = color, darkIcons = useDarkIcons )
}