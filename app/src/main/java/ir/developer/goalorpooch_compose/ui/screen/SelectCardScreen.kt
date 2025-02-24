package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
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
import ir.developer.goalorpooch_compose.ui.theme.sizePicLarge
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundBottomSheet
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundMax
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel
import ir.developer.goalorpooch_compose.util.ManegeGame
import ir.developer.goalorpooch_compose.util.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun SelectCardScreen(
    idItemSelected: Int,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val numberCards = sharedViewModel.allCards.value.size
    val remainingCount =
        remember { mutableIntStateOf(sharedViewModel.itemSetting.value.countOfPlayingCards) }
    val selectedState =
        remember { mutableStateListOf(*Array(numberCards) { false }) }

    if (idItemSelected == 0) {
        ManegeGame.team_one_has_card = true
    } else {
        ManegeGame.team_two_has_card = true
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
                    )
            ) {
                AppBar(
                    title = stringResource(R.string.distribution_of_cards),
                    showBackButton = false,
                    navController = navController
                )
                Row(
                    modifier = Modifier.padding(
                        top = paddingTop(),
                        start = paddingRound(),
                        end = paddingRound()
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(sizePicMedium()),
                        painter = painterResource(
                            id = if (idItemSelected == 0)
                                R.drawable.pic_team_one
                            else
                                R.drawable.pic_team_two
                        ),
                        contentDescription = "pic_team_one"
                    )
                    Text(
                        modifier = Modifier.padding(start = paddingRound()),
                        text = if (idItemSelected == 0)
                            stringResource(R.string.team_one)
                        else
                            stringResource(R.string.team_two),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }
                Text(
                    modifier = Modifier.padding(
                        top = paddingTop(),
                        start = paddingRound(),
                        end = paddingRound()
                    ),
                    text = if (idItemSelected == 0)
                        "تیم اول بازی کارت های خود را انتخاب کند .شما باید ${Utils.THE_NUMBER_OF_PLAYING_CARDS} کارت از کارت های زیر را انتخاب کنید."
                    else
                        "تیم دوم بازی کارت های خود را انتخاب کند .شما باید ${Utils.THE_NUMBER_OF_PLAYING_CARDS} کارت از کارت های زیر را انتخاب کنید.",
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(
                            top = paddingTopMedium(),
                            start = paddingRound(),
                            end = paddingRound()
                        )
                        .fillMaxWidth()
                        .weight(1f),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(10.sdp),
                    horizontalArrangement = Arrangement.spacedBy(10.sdp)
                ) {
                    items(numberCards) { index ->
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

                Button(
                    modifier = Modifier
                        .padding(paddingRound())
                        .fillMaxWidth()
                        .height(heightButton())
                        .align(Alignment.CenterHorizontally)
                        .alpha(if (remainingCount.intValue != 0) 0.7f else 1f),
                    colors = ButtonColors(
                        containerColor = FenceGreen,
                        contentColor = Color.White,
                        disabledContainerColor = HihadaBrown,
                        disabledContentColor = HihadaBrown
                    ),
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(sizeRoundMax()),
                    contentPadding = PaddingValues(0.dp),
                    enabled = remainingCount.intValue == 0,
                    onClick = { navController.navigate("${Utils.SHOW_SELECTED_CARD_SCREEN}/${idItemSelected}") }) {
                    Text(
                        text = stringResource(R.string.btn_ok, remainingCount.intValue),
                        color = Color.White,
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
fun CardItem(isSelected: Boolean, onCardClick: () -> Unit) {
    val rotation by animateFloatAsState(
        targetValue = if (isSelected) 180f else 0f,
        animationSpec = tween(durationMillis = 500), label = ""
    )
    val alpha by animateFloatAsState(
        targetValue = if (isSelected) 0.5f else 1f,
        animationSpec = tween(durationMillis = 500), label = ""
    )

    Box(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) { onCardClick() }
            .background(Color.Transparent)
            .graphicsLayer(
                rotationY = rotation,
                alpha = alpha
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.pic_card),
            contentDescription = null,
            modifier = Modifier
                .width(sizePicLarge())
                .alpha(if (isSelected) 0.4f else 1f),
            contentScale = ContentScale.FillWidth
        )
    }
}

//@Preview
//@Composable
//private fun SelectCardScreenPreview() {
//    val navController = rememberNavController()
//    SelectCardScreen(idItemSelected = 1, navController = navController)
//}