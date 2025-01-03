package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.SharedViewModel
import ir.developer.goalorpooch_compose.Utils
import ir.developer.goalorpooch_compose.model.CardModelTeamOne
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
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var selectedCount by remember { mutableIntStateOf(0) }
    val maxSelection = Utils.THE_NUMBER_OF_PLAYING_CARDS

    LaunchedEffect(Unit) {
        sharedViewModel.getAllCardsTeamOne() // فراخوانی تابع برای دریافت داده‌ها
    }
    val cards by sharedViewModel.allCardsTeamOne.collectAsState(initial = emptyList()) // دریافت داده‌ها به صورت State

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

                CardGrid(cards = cards, onCardClick = { card ->
                    if (selectedCount < maxSelection && !card.disable) {
                        card.disable = true
                        selectedCount++
                        sharedViewModel.updateCardTeamOne(
                            card.id,
                            card.name,
                            card.description,
                            isSelect = true,
                            disable = true
                        )
                    }
                })

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    modifier = Modifier
                        .padding(PaddingRound())
                        .fillMaxWidth()
                        .height(HeightButton())
                        .align(Alignment.CenterHorizontally)
                        .alpha(if (selectedCount != maxSelection) 0.8f else 1f),
                    colors = ButtonColors(
                        containerColor = FenceGreen,
                        contentColor = Color.White,
                        disabledContainerColor = HihadaBrown,
                        disabledContentColor = HihadaBrown
                    ),
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(100f),
                    contentPadding = PaddingValues(0.dp),
                    enabled = selectedCount == maxSelection,
                    onClick = { navController.navigate(Utils.SHOW_SELECTED_CARD_SCREEN) }) {
                    Text(
                        text = "تایید($selectedCount)",
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
fun CardGrid(cards: List<CardModelTeamOne>, onCardClick: (CardModelTeamOne) -> Unit) {
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
        items(cards.size) { index ->
            val card = cards[index] // دریافت مدل کارت با استفاده از ایندکس
            CardItem(card = card, onCardClick = onCardClick)
        }
    }
}

@Composable
fun CardItem(card: CardModelTeamOne, onCardClick: (CardModelTeamOne) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !card.disable) { onCardClick(card) }
            .alpha(if (card.disable) 0.3f else 1f),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(card.imageTeamOne),
                contentDescription = card.description,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


//@Preview
//@Composable
//fun SelectCardScreenPreview() {
//    val navController = rememberNavController()
//    SelectCardScreen(navController = navController, sharedViewModel = sharedViewModel)
//}