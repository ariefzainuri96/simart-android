package simart.umby.android.component.compose

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import simart.umby.android.R
import simart.umby.android.component.compose.theme.CustomRippleConfiguration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableCard(
    backgroundColor: Int = R.color.transparent,
    modifier: Modifier = Modifier,
    isDefaultExpanded: Boolean = false,
    padding: Dp = 0.dp,
    contentExpandedSpacerHeight: Dp = 8.dp,
    headerVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    header: @Composable () -> Unit,
    expandableContent: @Composable () -> Unit
) {
    var expandedState by remember { mutableStateOf(isDefaultExpanded) }

    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f, label = ""
    )

    CompositionLocalProvider(LocalRippleConfiguration provides CustomRippleConfiguration) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = LinearOutSlowInEasing
                    )
                )
                .padding(padding)
                .clickable {
                    expandedState = !expandedState
                },
            colors = CardDefaults.cardColors(
                containerColor = colorResource(backgroundColor),
            ),
        ) {
            Column {
                Row(verticalAlignment = headerVerticalAlignment) {
                    Box(modifier = Modifier.weight(1f)) {
                        header()
                    }

                    Icon(
                        painter = painterResource(R.drawable.ic_expand), contentDescription = null,
                        tint = colorResource(R.color.textPrimary),
                        modifier = Modifier.rotate(rotationState)
                    )
                }

                // put your expanded content here
                if (expandedState) {
                    Spacer(Modifier.height(contentExpandedSpacerHeight))

                    expandableContent()
                }
            }
        }
    }
}