package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium_two
import ir.developer.goalorpooch_compose.ui.theme.sizePicSmall
import ir.developer.goalorpooch_compose.ui.theme.sizePicVerySmall
import ir.developer.goalorpooch_compose.ui.theme.sizeRound
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundBottomSheet
import ir.developer.goalorpooch_compose.ui.theme.titleSize
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel
import ir.developer.goalorpooch_compose.util.Utils
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartGameScreen(modifier: Modifier = Modifier, navController: NavController,sharedViewModel: SharedViewModel) {
    var showBottomSheetExitGame by remember { mutableStateOf(false) }
    var showBottomSheetOpeningDuel by remember { mutableStateOf(false) }
    val sheetStateExitGame = rememberModalBottomSheetState()
    val sheetStateOpeningDuel = rememberModalBottomSheetState(
        //برای زمانی که روی صفحه کلیک کرد باتشیت دیس میس نشه
        skipPartiallyExpanded = false,
        confirmValueChange = { false }
    )
    val scope = rememberCoroutineScope()

    var scoreTeamOne by remember { mutableIntStateOf(0) }
    var scoreTeamTwo by remember { mutableIntStateOf(0) }

    Log.i("StartGameScreen0", "StartGameScreen0: ${sharedViewModel.getTeam(0)}")
    Log.i("StartGameScreen0", "StartGameScreen1: ${sharedViewModel.getTeam(1)}")
    BackHandler {
        showBottomSheetExitGame = true
    }

    Scaffold { innerPadding ->
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(R.drawable.main_background),
                        contentScale = ContentScale.Crop
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                HeaderGame(scoreTeamOne = scoreTeamOne, scoreTeamTwo = scoreTeamTwo)
                TableGame()

//time
                Button(
                    modifier = modifier
                        .width(130.sdp)
                        .height(50.sdp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = FenceGreen),
                    shape = RoundedCornerShape(sizeRound()),
                    border = BorderStroke(width = 1.sdp, color = Color.White)
                ) {
                    Text(
                        modifier = modifier.padding(end = paddingRound()),
                        text = stringResource(R.string.start_time),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White
                    )

                    Icon(
                        painter = painterResource(R.drawable.time),
                        contentDescription = "time",
                        modifier = modifier.size(
                            sizePicVerySmall()
                        )
                    )
                }

                Row(
                    modifier = modifier.padding(
                        top = paddingTopMedium(),
                        start = paddingRound(),
                        end = paddingRound(),
                        bottom = paddingRound()
                    ),
                    horizontalArrangement = Arrangement.spacedBy(5.sdp)
                ) {
                    Column(
                        modifier = modifier
                            .size(75.sdp)
                            .background(
                                color = FenceGreen,
                                shape = RoundedCornerShape(sizeRound())
                            )
                            .padding(8.sdp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            text = "3",
                            fontSize = descriptionSize(),
                            fontFamily = FontPeydaMedium,
                            color = Color.White
                        )

                        Icon(
                            painter = painterResource(R.drawable.hand),
                            contentDescription = "time",
                            modifier = modifier.size(
                                sizePicVerySmall()
                            ), tint = Color.White
                        )
                        Text(
                            text = stringResource(R.string.empty_game),
                            fontSize = descriptionSize(),
                            fontFamily = FontPeydaMedium,
                            color = Color.White
                        )
                    }

                    Column(
                        modifier = modifier
                            .size(75.sdp)
                            .background(
                                color = FenceGreen,
                                shape = RoundedCornerShape(sizeRound())
                            )
                            .padding(8.sdp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            text = "5",
                            fontSize = descriptionSize(),
                            fontFamily = FontPeydaMedium,
                            color = Color.White
                        )

                        Icon(
                            painter = painterResource(R.drawable.icon_card),
                            contentDescription = "card",
                            modifier = modifier.size(
                                sizePicVerySmall()
                            ), tint = Color.White
                        )
                        Text(
                            text = stringResource(R.string.cards),
                            fontSize = descriptionSize(),
                            fontFamily = FontPeydaMedium,
                            color = Color.White
                        )
                    }

                    Column(
                        modifier = modifier
                            .size(75.sdp)
                            .background(
                                color = FenceGreen,
                                shape = RoundedCornerShape(sizeRound())
                            )
                            .padding(8.sdp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            modifier = modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            text = "2",
                            fontSize = descriptionSize(),
                            fontFamily = FontPeydaMedium,
                            color = Color.White
                        )

                        Icon(
                            painter = painterResource(R.drawable.cube),
                            contentDescription = "time",
                            modifier = modifier.size(
                                sizePicVerySmall()
                            ), tint = Color.White
                        )
                        Text(
                            text = stringResource(R.string.cube),
                            fontSize = descriptionSize(),
                            fontFamily = FontPeydaMedium,
                            color = Color.White
                        )
                    }
                }

//BottomSheetExitGame
                if (showBottomSheetExitGame) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheetExitGame = false },
                        sheetState = sheetStateExitGame,
                        shape = RoundedCornerShape(
                            topEnd = sizeRoundBottomSheet(),
                            topStart = sizeRoundBottomSheet()
                        ),
                        containerColor = FenceGreen
                    ) {
                        BottomSheetContactExitGame(
                            onClickExit = {
                                scope.launch { sheetStateExitGame.hide() }
                                    .invokeOnCompletion { showBottomSheetExitGame = false }
                                navController.navigate(Utils.HOME_SCREEN) {
                                    popUpTo(0) // پاک کردن کل استک
                                    launchSingleTop = true // جلوگیری از ایجاد دوباره صفحه در استک
                                }
                            },
                            onClickContinueGame = {
                                scope.launch { sheetStateExitGame.hide() }
                                    .invokeOnCompletion { showBottomSheetExitGame = false }
                            }
                        )
                    }
                }

//BottomSheetOpeningDuel
                if (showBottomSheetOpeningDuel) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheetOpeningDuel = false
                        },
                        sheetState = sheetStateOpeningDuel,
                        shape = RoundedCornerShape(
                            topEnd = sizeRoundBottomSheet(),
                            topStart = sizeRoundBottomSheet()
                        ),
                        containerColor = FenceGreen,
                        properties = ModalBottomSheetProperties(
                            securePolicy = SecureFlagPolicy.SecureOff,
                            shouldDismissOnBackPress = false
                        )
                    ) {
                        BottomSheetContactTheOpeningDuelOfTheGame(
                            whichTeamHasGoal = Utils.STARTER_GAME,
                            onClickItem = { int ->
                                scope.launch {
                                    sheetStateOpeningDuel.hide()
                                }.invokeOnCompletion {
                                    if (!sheetStateOpeningDuel.isVisible) {
                                        showBottomSheetOpeningDuel = false
                                    }
                                }
                            },
                            onDismissRequest = {
                                scope.launch {
                                    sheetStateOpeningDuel.hide()
                                }.invokeOnCompletion {
                                    if (!sheetStateOpeningDuel.isVisible) {
                                        showBottomSheetOpeningDuel = false
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TableGame(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(320.sdp)
            .padding(paddingRound()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painterResource(R.drawable.person_one),
                contentDescription = null,
                modifier = modifier.size(
                    sizePicMedium_two()
                )
            )
            Image(
                painterResource(R.drawable.person_two),
                contentDescription = null,
                modifier = modifier.size(
                    sizePicMedium_two()
                )
            )
            Image(
                painterResource(R.drawable.person_three),
                contentDescription = null,
                modifier = modifier.size(
                    sizePicMedium_two()
                )
            )
        }
        Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxHeight()) {
            Image(
                painter = painterResource(R.drawable.table),
                contentDescription = "table",
                modifier = modifier.width(135.sdp),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "59 : 01",
                fontSize = titleSize(),
                fontFamily = FontPeydaBold,
                color = Color.White,
            )
        }

        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painterResource(R.drawable.person_four),
                contentDescription = null,
                modifier = modifier.size(
                    sizePicMedium_two()
                )
            )
            Image(
                painterResource(R.drawable.person_five),
                contentDescription = null,
                modifier = modifier.size(
                    sizePicMedium_two()
                )
            )
            Image(
                painterResource(R.drawable.person_six),
                contentDescription = null,
                modifier = modifier.size(
                    sizePicMedium_two()
                )
            )
        }
    }
}

@Composable
fun HeaderGame(modifier: Modifier = Modifier, scoreTeamOne: Int, scoreTeamTwo: Int) {
    Row(
        modifier = modifier
            .padding(paddingRound())
            .fillMaxWidth()
            .height(50.sdp)
            .background(color = FenceGreen, shape = RoundedCornerShape(sizeRound())),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(R.drawable.pic_team_one),
            contentDescription = "pic_one",
            modifier = modifier.size(
                sizePicSmall()
            )
        )
        Text(
            text = stringResource(R.string.team_one),
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )
        Text(
            text = "$scoreTeamOne",
            fontSize = descriptionSize(),
            fontFamily = FontPeydaBold,
            color = Color.White
        )
        VerticalDivider(modifier.height(25.sdp), thickness = 2.sdp)
        Text(
            text = "$scoreTeamTwo",
            fontSize = descriptionSize(),
            fontFamily = FontPeydaBold,
            color = Color.White
        )
        Text(
            text = stringResource(R.string.team_two),
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )
        Image(
            painter = painterResource(R.drawable.pic_team_two),
            contentDescription = "pic_two",
            modifier = modifier.size(
                sizePicSmall()
            )
        )
    }
}

//@Preview
//@Composable
//private fun StartGameScreenPreview() {
//    val navController = rememberNavController()
//    StartGameScreen(navController = navController)
//}