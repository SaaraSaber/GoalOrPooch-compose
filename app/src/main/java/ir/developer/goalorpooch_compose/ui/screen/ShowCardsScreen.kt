package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.HihadaBrown
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.fontSizeButton
import ir.developer.goalorpooch_compose.ui.theme.heightButton
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicSmall
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundBottomSheet
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundMax
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel
import ir.developer.goalorpooch_compose.util.ManegeGame
import ir.developer.goalorpooch_compose.util.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalMaterial3Api
@Composable
fun ShowCardsScreen(
    idItemSelected: Int,
    sharedViewModel: SharedViewModel,
    navController: NavController
) {
    val nextHowSeeCards: Int = if (idItemSelected == 0) {
        1
    } else {
        0
    }
    val randomItem: List<CardModel> = if (idItemSelected == 0) {
        sharedViewModel.assignRandomCardsToTeam(teamId = 0, sharedViewModel.allCards.value)
    } else {
        sharedViewModel.assignRandomCardsToTeam(teamId = 1, sharedViewModel.allCards.value)
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    BackHandler {
        showBottomSheet = true
    }

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
                AppBar(
                    title = stringResource(R.string.show_cards),
                    showBackButton = false,
                    navController = navController
                )
                Image(
                    modifier = Modifier
                        .padding(paddingTop())
                        .size(sizePicMedium()),
                    painter = painterResource(
                        id =
                        if (idItemSelected == 0)
                            R.drawable.pic_team_one
                        else
                            R.drawable.pic_team_two
                    ),
                    contentDescription = "pic"
                )
                Text(
                    text = if (idItemSelected == 0)
                        stringResource(R.string.team_one)
                    else
                        stringResource(R.string.team_two),
                    color = Color.White,
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium
                )

                LazyColumn(
                    modifier = Modifier
                        .padding(
                            top = paddingTopMedium(),
                            start = paddingRound(),
                            end = paddingRound()
                        )
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.sdp),
                ) {
                    items(randomItem.size) { index ->
                        val card = randomItem[index]
                        ItemSelectedCard(
                            card = card,
                            isLastItem = index == randomItem.size - 1
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(paddingRound())
                        .fillMaxWidth()
                        .height(heightButton())
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonColors(
                        containerColor = FenceGreen,
                        contentColor = Color.White,
                        disabledContainerColor = HihadaBrown,
                        disabledContentColor = HihadaBrown
                    ),
                    border = BorderStroke(1.sdp, Color.White),
                    shape = RoundedCornerShape(sizeRoundMax()),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {
                        if (ManegeGame.team_one_has_card && ManegeGame.team_two_has_card) {
                            navController.navigate(Utils.START_GAME_SCREEN)
                        } else {
                            navController.navigate("${Utils.SELECT_CARD_SCREEN}/$nextHowSeeCards")
                        }
                    }
                ) {
                    Text(
                        text = if (ManegeGame.team_one_has_card && ManegeGame.team_two_has_card)
                            stringResource(R.string.start_game)
                        else
                            stringResource(R.string.next_level),
                        fontSize = fontSizeButton(),
                        fontFamily = FontPeydaBold
                    )
                }

                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        shape = RoundedCornerShape(
                            topEnd = sizeRoundBottomSheet(),
                            topStart = sizeRoundBottomSheet()
                        ),
                        containerColor = FenceGreen
                    ) {
                        BottomSheetContactExitGame(
                            onClickExit = {
                                scope.launch { sheetState.hide() }
                                    .invokeOnCompletion { showBottomSheet = false }
                                navController.navigate(Utils.HOME_SCREEN) {
                                    popUpTo(0) // پاک کردن کل استک
                                    launchSingleTop = true // جلوگیری از ایجاد دوباره صفحه در استک
                                }
                            },
                            onClickContinueGame = {
                                scope.launch { sheetState.hide() }
                                    .invokeOnCompletion { showBottomSheet = false }
                            }
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun ItemSelectedCard(card: CardModel, isLastItem: Boolean, modifier: Modifier = Modifier) {
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
                        .width(sizePicSmall()),
                    painter = painterResource(id = R.drawable.pic_card),
                    contentDescription = "image"
                )
                Text(
                    text = card.name,
                    color = Color.White,
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaBold
                )
            }
            Text(
                text = card.description, color = Color.White,
                fontSize = descriptionSize(),
                fontFamily = FontPeydaMedium,
                textAlign = TextAlign.Justify

            )
            if (!isLastItem) {
                HorizontalDivider(
                    modifier = modifier
                        .padding(
                            top = paddingTopMedium(),
                            bottom = paddingTop(),
                            end = 50.sdp,
                            start = 50.sdp
                        ),
                    thickness = DividerDefaults.Thickness
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun ShowCardsScreenPreview() {
//    val navController = rememberNavController()
//    ShowCardsScreen(navController = navController)
//}