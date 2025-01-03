package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.Utils
import ir.developer.goalorpooch_compose.ui.theme.DescriptionSize
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.FontSizeButton
import ir.developer.goalorpooch_compose.ui.theme.HeightButton
import ir.developer.goalorpooch_compose.ui.theme.HihadaBrown
import ir.developer.goalorpooch_compose.ui.theme.PaddingRound
import ir.developer.goalorpooch_compose.ui.theme.PaddingTop
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopMedium
import ir.kaaveh.sdpcompose.sdp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .paint(
                        painterResource(id = R.drawable.main_background),
                        contentScale = ContentScale.Crop
                    )
            ) {
                AppBar(title = "تنظیمات بازی")

                Text(
                    modifier = Modifier.padding(
                        top = PaddingTop(),
                        start = PaddingRound(),
                        end = PaddingRound()
                    ),
                    text = "در این بخش می توانید بازی را برای خود شخصی سازی کنید، تنظیمات اولیه ی بازی را مشخص کنید.",
                    fontSize = DescriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                ListSettings()

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .padding(PaddingRound())
                        .fillMaxWidth()
                        .height(HeightButton())
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonColors(
                        containerColor = FenceGreen,
                        contentColor = Color.White,
                        disabledContainerColor = HihadaBrown,
                        disabledContentColor = HihadaBrown
                    ),
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(100f),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { navController.navigate(Utils.STARTER_SCREEN) }) {
                    Text(
                        text = "مرحله بعد",
                        fontSize = FontSizeButton(),
                        fontFamily = FontPeydaBold
                    )
                }
            }

        }
    }
}


@Composable
fun ListSettings() {
    val counters = remember { mutableStateListOf(6, 10, 30, 60, 5) }

    Column(
        modifier = Modifier
            .padding(
                top = PaddingTopMedium(), start = PaddingRound(), end = PaddingRound()
            )
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.sdp)
    ) {
        val labels = listOf(
            "تعداد بازیکنان",
            "امتیاز پیروزی",
            "زمان گرفتن گل",
            "زمان گرفتن شاه گل",
            "تعداد کارت های بازی"
        )
        labels.forEachIndexed { index, label ->
            CounterRow(
                label = label,
                count = counters[index],
                onIncrement = {
                    when (index) {
                        0 -> {
                            counters[index] += 2
                            Utils.COUNT_PLAYER = counters[index]
                        }

                        1 -> {
                            counters[index]++
                            Utils.VICTORY_POINTS = counters[index]
                        }

                        2 -> {
                            counters[index] += 5
                            Utils.TIME_TO_GET_GOAL = counters[index]
                        }

                        3 -> {
                            counters[index] += 5
                            Utils.TIME_TO_GET_SHAH_GOAL = counters[index]
                        }

                        4 -> {
                            counters[index]++
                            Utils.THE_NUMBER_OF_PLAYING_CARDS = counters[index]
                        }

                        else -> {}
                    }
                },
                onDecrement = {
                    when (index) {
                        0 -> {
                            if (counters[index] > 0) {
                                counters[index] -= 2
                                Utils.COUNT_PLAYER = counters[index]
                            }
                        }

                        1 -> {
                            if (counters[index] > 0) {
                                counters[index]--
                                Utils.VICTORY_POINTS = counters[index]
                            }
                        }

                        2 -> {
                            if (counters[index] > 0) {
                                counters[index] -= 5
                                Utils.TIME_TO_GET_GOAL = counters[index]
                            }
                        }

                        3 -> {
                            if (counters[index] > 0) counters[index] -= 5
                            Utils.TIME_TO_GET_SHAH_GOAL = counters[index]
                        }

                        4 -> {
                            if (counters[index] > 0) counters[index]--
                            Utils.THE_NUMBER_OF_PLAYING_CARDS = counters[index]
                        }

                        else -> {}
                    }
//                    if (counters[index] > 0) counters[index]-- // جلوگیری از مقادیر منفی
                }
            )
        }

    }
}

@Composable
fun CounterRow(label: String, count: Int, onIncrement: () -> Unit, onDecrement: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Start,
            fontSize = DescriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedButton(
                modifier = Modifier.size(25.sdp),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                shape = CircleShape,
                contentPadding = PaddingValues(4.dp),
                onClick = { onIncrement() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.increase),
                    contentDescription = null,
                    tint = Color.White
                )
            }
            Text(
                modifier = Modifier
                    .padding(start = 8.sdp, end = 8.sdp)
                    .align(Alignment.Bottom)
                    .width(40.sdp),
                text = count.toString(),
                textAlign = TextAlign.Center,
                fontSize = DescriptionSize(),
                fontFamily = FontPeydaMedium,
                color = Color.White
            )
            ElevatedButton(
                modifier = Modifier.size(25.sdp),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                shape = CircleShape,
                contentPadding = PaddingValues(4.dp),
                onClick = { onDecrement() }) {
                Icon(
                    painter = painterResource(id = R.drawable.decrease),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    val navController = rememberNavController()
    SettingScreen(navController)
}