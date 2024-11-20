package simart.umby.android.component.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.SfPro300
import simart.umby.android.component.compose.theme.SfPro400

@Composable
fun CustomTextField(
    value: String, label: String? = null, imeAction: ImeAction? = null, keyboardType:
    KeyboardType? = null, placeholder:
    String? = null, maxLines: Int? = null, minLines: Int? = null, onValueChange: (String) -> Unit,
    singleLine: Boolean = true, enable: Boolean = true, backgroundColor: Color? =
        null, error: String? = null, visualTransformation: VisualTransformation =
        VisualTransformation.None
) {
    Column {
        if (label != null) {
            Text(
                text = label,
                style = SfPro400.copy(colorResource(R.color.grey3), fontSize = 12.sp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(4.dp))
        }

        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            colors = TextFieldDefaults.colors(
                //setting the text field background when it is focused
                focusedContainerColor = backgroundColor ?: colorResource(R.color.white),
                //setting the text field background when it is unfocused or initial state
                unfocusedContainerColor = backgroundColor ?: colorResource(R.color.white),
                //setting the text field background when it is disabled
                disabledContainerColor = colorResource(R.color.disabledBackground),
                focusedIndicatorColor = colorResource(R.color.transparent),
                unfocusedIndicatorColor = colorResource(R.color.transparent),
            ),
            visualTransformation = visualTransformation, // use for
            // password -> PasswordVisualTransformation()
            maxLines = maxLines ?: 1,
            minLines = minLines ?: 1,
            textStyle = SfPro300,
            placeholder = {
                if (placeholder != null) {
                    Text(placeholder, style = SfPro300.copy(colorResource(R.color.grey3)))
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = imeAction ?: ImeAction.Done,
                keyboardType = keyboardType ?: KeyboardType.Text
            ),
            singleLine = singleLine,
            enabled = enable,
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = colorResource(R.color.lineSeparator2),
                    shape = RoundedCornerShape(4.dp),
                )
        )

        if (error != null) {
            Text(
                text = error,
                style = SfPro300.copy(colorResource(R.color.red1), fontSize = 12.sp),
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }
    }
}