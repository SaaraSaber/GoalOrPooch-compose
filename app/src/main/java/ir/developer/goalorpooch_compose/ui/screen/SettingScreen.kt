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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.model.SettingModel
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.GrayDisable
import ir.developer.goalorpooch_compose.ui.theme.HihadaBrown
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.fontSizeButton
import ir.developer.goalorpooch_compose.ui.theme.heightButton
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicSmall
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundMax
import ir.developer.goalorpooch_compose.ui.theme.textSize
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel
import ir.developer.goalorpooch_compose.util.ManegeGame
import ir.developer.goalorpooch_compose.util.Utils
import ir.kaaveh.sdpcompose.sdp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val itemSetting by sharedViewModel.itemSetting.collectAsState()
    LaunchedEffect(Unit) {
        sharedViewModel.getAllCards()
        sharedViewModel.updateTeam(teamId = 0) {
            copy(
                id = 0,
                score = 0,
                hasGoal = false,
                numberOfEmptyGames = 3,
                numberCubes = 2,
                cards = emptyList(),
                selectedCube = false,
                selectedCard = false,
                gotGoalDuel = 0,
                notGotGoalDuel = 0
            )
        }
        sharedViewModel.updateTeam(teamId = 1) {
            copy(
                id = 1,
                score = 0,
                hasGoal = false,
                numberOfEmptyGames = 3,
                numberCubes = 2,
                cards = emptyList(),
                selectedCube = false,
                selectedCard = false,
                gotGoalDuel = 0,
                notGotGoalDuel = 0
            )
        }
        ManegeGame.team_one_has_card = false
        ManegeGame.team_two_has_card = false
        Utils.WIN_TEAM_TWO = false
        Utils.WIN_TEAM_ONE = false
        sharedViewModel.updateItemSetting(
            itemSetting.copy(
                shahGoal = false,
                countShahGoal = 0,
                duel = false
            )
        )
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
                    title = stringResource(R.string.setting),
                    showBackButton = true,
                    navController = navController
                )

                Text(
                    modifier = Modifier.padding(
                        top = paddingTop(),
                        start = paddingRound(),
                        end = paddingRound()
                    ),
                    text = stringResource(R.string.description_setting),
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                ListSettings(sharedViewModel = sharedViewModel, itemSetting = itemSetting)

                Spacer(modifier = Modifier.weight(1f))

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
                    border = BorderStroke(1.dp, Color.White),
                    shape = RoundedCornerShape(sizeRoundMax()),
                    contentPadding = PaddingValues(0.dp),
                    onClick = { navController.navigate(Utils.STARTER_SCREEN) }) {
                    Text(
                        text = stringResource(R.string.next_level),
                        fontSize = fontSizeButton(),
                        fontFamily = FontPeydaBold
                    )
                }
            }

        }
    }
}


@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Composable
fun ListSettings(sharedViewModel: SharedViewModel, itemSetting: SettingModel) {
//    val itemSetting by sharedViewModel.itemSetting.collectAsState()
    Column(
        modifier = Modifier
            .padding(
                top = paddingTopMedium(), start = paddingRound(), end = paddingRound()
            )
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.sdp)
    ) {
        ItemSetting(
            label = stringResource(R.string.number_player),
            count = itemSetting.playerNumber,
            minCount = 4,
            maxCount = 10,
            onIncrement = {
                sharedViewModel.updateItemSetting(
                    itemSetting.copy(playerNumber = itemSetting.playerNumber + 2)
                )
            },
            onDecrement = {
                if (itemSetting.playerNumber > 4) {
                    sharedViewModel.updateItemSetting(itemSetting.copy(playerNumber = itemSetting.playerNumber - 2))
                }
            }
        )
        ItemSetting(
            label = stringResource(R.string.victory_points),
            count = itemSetting.victoryPoint,
            minCount = 4,
            maxCount = 21,
            onIncrement = {
                sharedViewModel.updateItemSetting(
                    itemSetting.copy(victoryPoint = itemSetting.victoryPoint + 1)
                )
            },
            onDecrement = {
                if (itemSetting.victoryPoint > 4) {
                    sharedViewModel.updateItemSetting(itemSetting.copy(victoryPoint = itemSetting.victoryPoint - 1))
                }
            }
        )
        ItemSetting(
            label = stringResource(R.string.time_get_goal),
            count = itemSetting.getTimeToGetGoal,
            minCount = 30,
            maxCount = 600,
            onIncrement = {
                sharedViewModel.updateItemSetting(
                    itemSetting.copy(getTimeToGetGoal = itemSetting.getTimeToGetGoal + 10)
                )
            },
            onDecrement = {
                if (itemSetting.getTimeToGetGoal > 30) {
                    sharedViewModel.updateItemSetting(itemSetting.copy(getTimeToGetGoal = itemSetting.getTimeToGetGoal - 10))
                }
            }
        )
        ItemSetting(
            label = stringResource(R.string.time_get_king_goal),
            count = itemSetting.getTimeToGetShahGoal,
            minCount = 30,
            maxCount = 600,
            onIncrement = {
                sharedViewModel.updateItemSetting(
                    itemSetting.copy(getTimeToGetShahGoal = itemSetting.getTimeToGetShahGoal + 10)
                )
            },
            onDecrement = {
                if (itemSetting.getTimeToGetShahGoal > 30) {
                    sharedViewModel.updateItemSetting(itemSetting.copy(getTimeToGetShahGoal = itemSetting.getTimeToGetShahGoal - 10))
                }
            }
        )
        ItemSetting(
            label = stringResource(R.string.number_cards),
            count = itemSetting.countOfPlayingCards,
            minCount = 5,
            maxCount = 9,
            onIncrement = {
                sharedViewModel.updateItemSetting(
                    itemSetting.copy(countOfPlayingCards = itemSetting.countOfPlayingCards + 1)
                )
            },
            onDecrement = {
                if (itemSetting.countOfPlayingCards > 5) {
                    sharedViewModel.updateItemSetting(itemSetting.copy(countOfPlayingCards = itemSetting.countOfPlayingCards - 1))
                }
            }
        )
    }
}

@Composable
fun ItemSetting(
    label: String,
    count: Int,
    minCount: Int,
    maxCount: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Start,
            fontSize = textSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedButton(
                modifier = Modifier
                    .size(sizePicSmall()),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = GrayDisable,
                    disabledContentColor = GrayDisable
                ),
                shape = CircleShape,
                contentPadding = PaddingValues(4.dp),
                enabled = count < maxCount,
                onClick = { onIncrement() }
            ) {
                Icon(
                    modifier = Modifier.size(12.sdp),
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
                fontSize = textSize(),
                fontFamily = FontPeydaMedium,
                color = Color.White
            )
            ElevatedButton(
                modifier = Modifier
                    .size(sizePicSmall()),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = GrayDisable,
                    disabledContentColor = GrayDisable
                ),
                shape = CircleShape,
                contentPadding = PaddingValues(4.dp),
                enabled = count > minCount,
                onClick = { onDecrement() }
            ) {
                Icon(
                    modifier = Modifier.size(12.sdp),
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
    ItemSetting(
        label = "hello",
        count = 5,
        minCount = 5,
        maxCount = 600,
        onIncrement = {},
        onDecrement = {})
}