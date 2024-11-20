package simart.umby.android.component.compose.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import simart.umby.android.utils.Utils

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val SfPro300 = TextStyle(
    fontFamily = Utils.FontName,
    fontWeight = FontWeight.Light,
    fontSize = 14.sp,
    color = gray1)
val SfPro400 = TextStyle(
    fontFamily = Utils.FontName,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    color = gray1
)
val SfPro500 = TextStyle(
    fontFamily = Utils.FontName,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    color = gray1
)
val SfPro600 = TextStyle(
    fontFamily = Utils.FontName,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    color = gray1
)
val SfPro700 = TextStyle(
    fontFamily = Utils.FontName,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    color = gray1
)
val SfPro900 = TextStyle(
    fontFamily = Utils.FontName,
    fontWeight = FontWeight.Black,
    fontSize = 14.sp,
    color = gray1
)