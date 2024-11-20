package simart.umby.android.component.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro600

enum class ButtonType {
    Primary,
    Outlined
}

@Composable
fun CustomButton(
    buttonType: ButtonType, borderColor: Color = colorResource(R.color.primary), value:
    String, backgroundColor: Color = colorResource(R.color.primary),
    modifier: Modifier = Modifier, onClick: () -> Unit
) {
    if (buttonType == ButtonType.Primary) {
        Button(
            modifier = modifier,
            onClick = onClick,
            contentPadding = PaddingValues(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(value, style = SfPro600.copy(colorResource(R.color.white)))
        }
    }

    if (buttonType == ButtonType.Outlined) {
        OutlinedButton(
            onClick = onClick,
            contentPadding = PaddingValues(16.dp),
            modifier = modifier,
            border = BorderStroke(1.dp, borderColor),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(value, style = SfPro600.copy(borderColor))
        }
    }
}