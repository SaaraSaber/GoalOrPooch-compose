package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import ir.developer.goalorpooch_compose.ui.theme.SizePicMedium
import ir.kaaveh.sdpcompose.sdp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun SelectCardScreen(
    idItemSelected: Int,
    navController: NavController
) {
    val remainingCount = remember { mutableIntStateOf(Utils.THE_NUMBER_OF_PLAYING_CARDS) }
//    val maxSelection = Utils.THE_NUMBER_OF_PLAYING_CARDS

    val selectedState = remember { mutableStateListOf(*Array(8) { false }) }

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
                AppBar(title = "توزیع کارت ها")
                Row(
                    modifier = Modifier.padding(
                        top = PaddingTop(),
                        start = PaddingRound(),
                        end = PaddingRound()
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(SizePicMedium()),
                        painter = painterResource(id = if (idItemSelected == 0) R.drawable.pic_team_one else R.drawable.pic_team_two),
                        contentDescription = "pic_team_one"
                    )
                    Text(
                        modifier = Modifier.padding(start = PaddingRound()),
                        text = if (idItemSelected == 0) "تیم اول" else "تیم دوم",
                        fontSize = DescriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
                Text(
                    modifier = Modifier.padding(
                        top = PaddingTop(),
                        start = PaddingRound(),
                        end = PaddingRound()
                    ),
                    text = if (idItemSelected == 0) "تیم اول بازی کارت های خود را انتخاب کند .شما باید 5 کارت از کارت های زیر را انتخاب کنید." else "تیم دوم بازی کارت های خود را انتخاب کند .شما باید 5 کارت از کارت های زیر را انتخاب کنید.",
                    fontSize = DescriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(
                            top = PaddingTopMedium(),
                            start = PaddingRound(),
                            end = PaddingRound()
                        )
                        .fillMaxWidth(),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(5.sdp),
                    horizontalArrangement = Arrangement.spacedBy(5.sdp)
                ) {
                    items(8) { index ->
                        CardItem(isSelected = selectedState[index],
                            onCardClick = {
                                if (!selectedState[index] && remainingCount.intValue > 0) {
                                    selectedState[index] = true
                                    remainingCount.value -= 1
                                } else if (selectedState[index]) {
                                    selectedState[index] = false
                                    remainingCount.value += 1
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .padding(PaddingRound())
                        .fillMaxWidth()
                        .height(HeightButton())
                        .align(Alignment.CenterHorizontally)
                        .alpha(if (remainingCount.intValue != 0) 0.8f else 1f),
                    colors = ButtonColors(
                        containerColor = FenceGreen,
                        contentColor = Color.White,
                        disabledContainerColor = HihadaBrown,
                        disabledContentColor = HihadaBrown
                    ),
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(100f),
                    contentPadding = PaddingValues(0.dp),
                    enabled = remainingCount.intValue == 0,
                    onClick = { navController.navigate(Utils.SHOW_SELECTED_CARD_SCREEN) }) {
                    Text(
                        text = "تایید(${remainingCount.intValue})",
                        color = Color.White,
                        fontSize = FontSizeButton(),
                        fontFamily = FontPeydaBold
                    )
                }
            }
        }
    }
}

@Composable
fun CardItem(isSelected: Boolean, onCardClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onCardClick() }
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.card_team_one),
            contentDescription = null,
            modifier = Modifier
                .width(80.sdp)
                .alpha(if (isSelected) 0.3f else 1f),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
private fun SelectCardScreenPreview() {
    val navController = rememberNavController()
    SelectCardScreen(idItemSelected = 1, navController = navController)
}