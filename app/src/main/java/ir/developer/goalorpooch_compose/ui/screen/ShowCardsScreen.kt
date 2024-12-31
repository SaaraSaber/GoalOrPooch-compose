package ir.developer.goalorpooch_compose.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.SizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.SizePicSmall
import ir.kaaveh.sdpcompose.sdp

@Composable
fun ShowCardsScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(Unit) {
        sharedViewModel.getCardsSelectTeamOne(true)
    }
    val cards by sharedViewModel.allCardSelectTeamOne.collectAsState(initial = emptyList())

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
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppBar(title = "نمایش کارت ها")
                Image(
                    modifier = Modifier
                        .padding(PaddingTop())
                        .size(SizePicMedium()),
                    painter = painterResource(id = R.drawable.pic_team_two),
                    contentDescription = "pic_team_one"
                )
                Text(
                    text = "تیم دوم",
                    color = Color.White,
                    fontSize = DescriptionSize(),
                    fontFamily = FontPeydaMedium
                )

                LazyColumn(
                    modifier = Modifier
                        .padding(
                            top = PaddingTopMedium(),
                            start = PaddingRound(),
                            end = PaddingRound()
                        )
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(5.sdp),
                ) {
                    items(cards.size) { index ->
                        val card = cards[index]
                        ItemSelectedCard(card = card)
                    }
                }
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
                    border = BorderStroke(1.sdp, Color.White),
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
fun ItemSelectedCard(card: CardModelTeamOne, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.sdp)
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.sdp)
            ) {
                Image(
                    modifier = Modifier
                        .width(SizePicSmall()),
                    painter = painterResource(id = card.imageTeamOne),
                    contentDescription = "pic_team_one"
                )
                Text(
                    text = card.name,
                    color = Color.White,
                    fontSize = DescriptionSize(),
                    fontFamily = FontPeydaBold
                )
            }
            Text(
                text = card.description, color = Color.White,
                fontSize = DescriptionSize(),
                fontFamily = FontPeydaMedium,
                textAlign = TextAlign.Justify

            )
            HorizontalDivider(
                modifier = modifier
                    .padding(top = PaddingTopLarge(), end = 50.sdp, start = 50.sdp),
                thickness = DividerDefaults.Thickness
            )
        }
    }
}

//@Preview
//@Composable
//fun ShowCardsScreenPreview() {
//    val navController = rememberNavController()
//    ShowCardsScreen(navController = navController)
//}