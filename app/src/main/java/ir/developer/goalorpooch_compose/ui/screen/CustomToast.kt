package ir.developer.goalorpooch_compose.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen1
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingRoundMini
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.toastSize
import ir.kaaveh.sdpcompose.sdp

@Composable
fun CustomToast(
    modifier: Modifier = Modifier,
    message: String,
    isVisible: Boolean,
    color: Int,
    icon: Int,
    duration: Long = 2000L,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingRound()), // فاصله از بالای صفحه
        contentAlignment = Alignment.TopCenter // قرارگیری در بالای صفحه
    ) {
        AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Card(
                    modifier = modifier
                        .padding(paddingRound())
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(paddingRoundMini()),
                    colors = CardDefaults.cardColors(containerColor = FenceGreen1),
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(paddingTop()),
                        horizontalArrangement = Arrangement.spacedBy(5.sdp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = null,
                            tint = colorResource(color)
                        )

                        VerticalDivider(
                            modifier = modifier.height(25.sdp),
                            color = colorResource(color)
                        )
                        Text(
                            text = message,
                            color = Color.White,
                            fontSize = toastSize(),
                            fontFamily = FontPeydaMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = true
                        )
                    }
                }
            }
            LaunchedEffect(isVisible) {
                if (isVisible) {
                    kotlinx.coroutines.delay(duration) // تاخیر ۲ ثانیه‌ای
                    onDismiss()
                }
            }
        }
    }
}

@Preview
@Composable
private fun CustomToastPreview() {
    CustomToast(
        message = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صن",
        isVisible = true,
        color = R.color.hihadaBrown,
        icon = R.drawable.close_circle,
        onDismiss = {})
}