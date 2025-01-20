package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.model.TeamModel
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.mediumAlpha
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartGameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var showBottomSheetExitGame by remember { mutableStateOf(false) }
    var showBottomSheetOpeningDuel by remember { mutableStateOf(true) }
    val sheetStateExitGame = rememberModalBottomSheetState()
    val sheetStateOpeningDuel = rememberModalBottomSheetState(
        //برای زمانی که روی صفحه کلیک کرد باتشیت دیس میس نشه
        skipPartiallyExpanded = true,
        confirmValueChange = { false }
    )
    val scope = rememberCoroutineScope()

    val teams by sharedViewModel.teams.collectAsState()

    val teamOne = teams[0]
    val teamTwo = teams[1]

    BackHandler {
        showBottomSheetExitGame = true
    }
    //timer
    var isRunning by remember { mutableStateOf(false) }
    var remainingTime by remember { mutableIntStateOf(sharedViewModel.itemSetting.value.getTimeToGetGoal) }
    var showBottomSheetResult by remember { mutableStateOf(false) }
    val sheetStateResult = rememberModalBottomSheetState(
        //برای زمانی که روی صفحه کلیک کرد باتشیت دیس میس نشه
        skipPartiallyExpanded = false,
        confirmValueChange = { false }
    )

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (remainingTime > 0) {
                delay(1000L)
                remainingTime -= 1
            }
            if (remainingTime == 0) {
                showBottomSheetResult = true
            }
            isRunning = false
//            remainingTime = sharedViewModel.itemSetting.value.getTimeToGetGoal
        }
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
                HeaderGame(
                    scoreTeamOne = teamOne.score,
                    scoreTeamTwo = teamTwo.score,
                    whichTeamHasGoal =
                    if (teamOne.hasGoal) {
                        0
                    } else if (teamTwo.hasGoal) {
                        1
                    } else {
                        2
                    }
                )
                TableGame(
                    whichTeamHasGoal =
                    if (teamOne.hasGoal) {
                        0
                    } else if (teamTwo.hasGoal) {
                        1
                    } else {
                        2
                    },
                    remainingTime = remainingTime,
                    showTimer = isRunning
                )

//time
                Button(
                    modifier = modifier
                        .wrapContentWidth()
                        .height(50.sdp),
                    onClick = {
                        if (isRunning) {
                            isRunning = false
                            showBottomSheetResult = true
                            remainingTime = sharedViewModel.itemSetting.value.getTimeToGetGoal
                        } else {
                            isRunning = true
                            remainingTime = sharedViewModel.itemSetting.value.getTimeToGetGoal
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = FenceGreen),
                    shape = RoundedCornerShape(sizeRound()),
                    border = BorderStroke(width = 1.sdp, color = Color.White)
                ) {
                    Text(
                        modifier = modifier.padding(end = paddingRound()),
                        text = if (!isRunning) stringResource(R.string.start_time)
                        else stringResource(R.string.result_of_this_round),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White
                    )

                    Icon(
                        painter = if (!isRunning) painterResource(R.drawable.time)
                        else painterResource(R.drawable.result),
                        contentDescription = "time",
                        modifier = modifier.size(
                            sizePicVerySmall()
                        )
                    )
                }

                if (teamOne.hasGoal) {
                    TeamInfoSection(team = teamOne)
                } else {
                    TeamInfoSection(team = teamTwo)
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
                            onClickItem = { idTeam ->
                                scope.launch {
                                    if (idTeam == 0) {
                                        sharedViewModel.updateTeam(teamId = 0) {
                                            copy(hasGoal = true)
                                        }
                                        sharedViewModel.updateTeam(teamId = 1) {
                                            copy(hasGoal = false)
                                        }
                                    } else if (idTeam == 1) {
                                        sharedViewModel.updateTeam(teamId = 1) {
                                            copy(hasGoal = true)
                                        }
                                        sharedViewModel.updateTeam(teamId = 0) {
                                            copy(hasGoal = false)
                                        }
                                    }
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

//BottomSheetResultThisRound
            if (showBottomSheetResult) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheetResult = false
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
                    BottomSheetResultOfThisRound(
                        whichTeamResult =
                        if (teamOne.hasGoal) {
                            1
                        } else if (teamTwo.hasGoal) {
                            0
                        } else {
                            2
                        },
                        sharedViewModel = sharedViewModel,
                        onClickItem = {
                            scope.launch {
                                sheetStateResult.hide()
                            }.invokeOnCompletion {
                                if (!sheetStateResult.isVisible) {
                                    showBottomSheetResult = false
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TeamInfoSection(modifier: Modifier = Modifier, team: TeamModel) {
    var count by remember { mutableIntStateOf(team.numberOfEmptyGames) }
    Row(
        modifier = modifier.padding(
            top = paddingTopMedium(),
            start = paddingRound(),
            end = paddingRound(),
            bottom = paddingRound()
        ),
        horizontalArrangement = Arrangement.spacedBy(5.sdp)
    ) {
        // تعداد خالی بازی
        InfoBox(
            value = count.toString(),
            icon = R.drawable.hand,
            label = stringResource(R.string.empty_game),
            onClickItem = { if (count != 0) count-- }
        )
        // تعداد کارت‌ها
        InfoBox(
            value = team.cards.size.toString(),
            icon = R.drawable.icon_card,
            label = stringResource(R.string.cards),
            onClickItem = {}
        )
        // تعداد مکعب
        InfoBox(
            value = team.numberCubes.toString(),
            icon = R.drawable.cube,
            label = stringResource(R.string.cube),
            onClickItem = {}
        )
    }
}

@Composable
fun InfoBox(
    modifier: Modifier = Modifier,
    value: String,
    icon: Int,
    label: String,
    onClickItem: () -> Unit
) {

    Column(
        modifier = modifier
            .size(75.sdp)
            .background(
                color = FenceGreen,
                shape = RoundedCornerShape(sizeRound())
            )
            .padding(8.sdp)
            .clickable { onClickItem() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            text = value,
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )

        Icon(
            painter = painterResource(icon),
            contentDescription = "time",
            modifier = modifier.size(
                sizePicVerySmall()
            ), tint = Color.White
        )
        Text(
            text = label,
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun TableGame(
    modifier: Modifier = Modifier,
    whichTeamHasGoal: Int,
    remainingTime: Int,
    showTimer: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(320.sdp)
            .padding(paddingRound()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .alpha(if (whichTeamHasGoal == 0) 1f else mediumAlpha()),
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
                modifier = modifier.width(100.sdp),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontFamily = FontPeydaBold)) { // فونت پیش‌فرض برای اعداد
                        append(
                            if (showTimer) String.format(
                                Locale.US,
                                "%02d:%02d",
                                remainingTime / 60,
                                remainingTime % 60
                            ) else ""
                        )
                    }
                },
                fontSize = titleSize(),
                fontFamily = FontPeydaBold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = modifier
                .fillMaxHeight()
                .alpha(if (whichTeamHasGoal == 1) 1f else mediumAlpha()),
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
fun HeaderGame(
    modifier: Modifier = Modifier,
    scoreTeamOne: Int,
    scoreTeamTwo: Int,
    whichTeamHasGoal: Int
) {
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
            modifier = modifier
                .size(
                    sizePicSmall()
                )
                .alpha(if (whichTeamHasGoal == 0) 1f else mediumAlpha())
        )
        Text(
            modifier = modifier.alpha(if (whichTeamHasGoal == 0) 1f else mediumAlpha()),
            text = stringResource(R.string.team_one),
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )
        Text(
            modifier = modifier.alpha(if (whichTeamHasGoal == 0) 1f else mediumAlpha()),
            text = "$scoreTeamOne",
            fontSize = descriptionSize(),
            fontFamily = FontPeydaBold,
            color = Color.White
        )
        VerticalDivider(modifier.height(25.sdp), thickness = 2.sdp)
        Text(
            modifier = modifier.alpha(if (whichTeamHasGoal == 1) 1f else mediumAlpha()),
            text = "$scoreTeamTwo",
            fontSize = descriptionSize(),
            fontFamily = FontPeydaBold,
            color = Color.White
        )
        Text(
            modifier = modifier.alpha(if (whichTeamHasGoal == 1) 1f else mediumAlpha()),
            text = stringResource(R.string.team_two),
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )
        Image(
            painter = painterResource(R.drawable.pic_team_two),
            contentDescription = "pic_two",
            modifier = modifier
                .size(
                    sizePicSmall()
                )
                .alpha(if (whichTeamHasGoal == 1) 1f else mediumAlpha())
        )
    }
}