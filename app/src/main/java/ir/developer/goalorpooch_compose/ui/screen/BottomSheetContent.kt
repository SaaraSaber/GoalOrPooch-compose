package ir.developer.goalorpooch_compose.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.model.AppModel
import ir.developer.goalorpooch_compose.model.ResultThisRoundModel
import ir.developer.goalorpooch_compose.model.TeamModel
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.heightButton
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingRoundMini
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.sizeIcon
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicSmall
import ir.developer.goalorpooch_compose.ui.theme.sizeRound
import ir.developer.goalorpooch_compose.ui.theme.titleSize
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel
import ir.developer.goalorpooch_compose.util.Utils
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun BottomSheetCube(
    modifier: Modifier = Modifier,
    whichTeamHasGoal: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit,
) {
    // State برای نگه داشتن انتخاب فعلی
    var selectedOption by remember { mutableStateOf<Int?>(null) }

    // گزینه‌های مکعب
    val options = listOf(2, 4, 6)

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = if (whichTeamHasGoal == 1)
                        painterResource(R.drawable.pic_team_one)
                    else
                        painterResource(R.drawable.pic_team_two),
                    contentDescription = null,
                    modifier = modifier.size(
                        sizePicSmall()
                    )
                )
                Text(
                    modifier = modifier.padding(start = paddingRoundMini()),
                    text = stringResource(R.string.score_cube),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = { onDismiss() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.close_circle),
                        contentDescription = "btn_close",
                        modifier = modifier.size(20.sdp)
                    )
                }
            }

            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Text(
                modifier = modifier.padding(top = paddingTopMedium(), bottom = paddingTopMedium()),
                text = if (whichTeamHasGoal == 1)
                    stringResource(R.string.description_score_cube_team_one)
                else
                    stringResource(R.string.description_score_cube_team_two),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )
            // نمایش گزینه‌ها
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(top = paddingTopMedium())
                    .fillMaxWidth()
            ) {
                options.forEach { option ->
                    Card(
                        modifier = modifier
                            .padding(start = paddingRoundMini())
                            .clickable { selectedOption = option }
                            .align(Alignment.CenterVertically)
                            .graphicsLayer(alpha = if (selectedOption == option) 1f else 0.5f),
                        colors = CardColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(sizeRound()),
                        border = BorderStroke(width = 1.sdp, color = Color.White)
                    ) {
                        Column(
                            modifier = modifier
                                .padding(
                                    top = paddingRoundMini(),
                                    bottom = paddingRoundMini(),
                                    start = paddingTopMedium(),
                                    end = paddingTopMedium()
                                ),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.cube),
                                contentDescription = "star",
                                modifier = modifier.size(sizePicSmall())
                            )
                            Text(
                                modifier = modifier.padding(
                                    top = paddingTop()
                                ),
                                text = option.toString(),
                                color = Color.White,
                                fontFamily = FontPeydaMedium,
                                fontSize = titleSize(),
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
            Row(modifier = Modifier.padding(top = paddingTopLarge())) {
                Button(
                    modifier = Modifier
                        .padding(end = 5.sdp)
                        .height(heightButton())
                        .weight(1f)
                        .alpha(if (selectedOption != null) 1f else .5f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        disabledContainerColor = Color.White
                    ),
                    enabled = selectedOption != null,
                    onClick = {
                        selectedOption?.let {
                            onConfirm(it)
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.yes),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = FenceGreen
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .padding(start = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    border = BorderStroke(1.sdp, Color.White),
                    onClick = onDismiss
                ) {
                    Text(
                        text = stringResource(R.string.cansel),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Composable
fun BottomSheetConfirmCube(
    modifier: Modifier = Modifier,
    whichTeamHasGoal: Int,
    numberCube: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = if (whichTeamHasGoal == 0)
                        painterResource(R.drawable.pic_team_one)
                    else
                        painterResource(R.drawable.pic_team_two),
                    contentDescription = null,
                    modifier = modifier.size(
                        sizePicSmall()
                    )
                )
                Text(
                    modifier = modifier.padding(start = paddingRoundMini()),
                    text = stringResource(R.string.confirmation),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
            }

            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Text(
                modifier = modifier.padding(top = paddingTopMedium(), bottom = paddingTopMedium()),
                text = if (whichTeamHasGoal == 0)
                    stringResource(R.string.description_confirm_score_cube_team_one, numberCube)
                else
                    stringResource(R.string.description_confirm_score_cube_team_two, numberCube),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )
            Row(modifier = Modifier.padding(top = paddingTopLarge(), bottom = paddingRound())) {
                Button(
                    modifier = Modifier
                        .padding(end = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = FenceGreen
                    ),
                    onClick = { onConfirm() }
                ) {
                    Text(
                        text = stringResource(R.string.accept_request),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = FenceGreen
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .padding(start = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    border = BorderStroke(1.sdp, Color.White),
                    onClick = onDismiss
                ) {
                    Text(
                        text = stringResource(R.string.no),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White
                    )
                }
            }

        }
    }
}

@Composable
fun BottomSheetCards(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onClickOk: (Int, Int) -> Unit,
    whichTeamHasGoal: Int,
    sharedViewModel: SharedViewModel
) {
    val card: TeamModel? = if (whichTeamHasGoal == 0) {
        sharedViewModel.getTeam(1)
    } else {
        sharedViewModel.getTeam(0)
    }
    val availableCards = card?.cards?.filter { !it.disable }
    // کارت انتخاب شده
    var selectedCardId by remember { mutableStateOf<Int?>(null) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_card),
                    contentDescription = null,
                )
                Text(
                    modifier = modifier.padding(start = paddingRoundMini()),
                    text = stringResource(R.string.choose_card),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = { onDismiss() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.close_circle),
                        contentDescription = "btn_close",
                        modifier = modifier.size(20.sdp)
                    )
                }
            }

            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(paddingRoundMini())
            ) {
                Image(
                    painter = if (whichTeamHasGoal == 1) painterResource(R.drawable.pic_team_one)
                    else painterResource(R.drawable.pic_team_two),
                    contentDescription = null,
                    modifier = modifier.size(
                        sizePicSmall()
                    )
                )
                Text(
                    modifier = modifier.padding(
                        top = paddingTopMedium(),
                        bottom = paddingTopMedium()
                    ),
                    text = if (whichTeamHasGoal == 1) stringResource(R.string.description_choose_card_team_one)
                    else stringResource(R.string.description_choose_card_team_two),
                    color = Color.White,
                    fontFamily = FontPeydaMedium,
                    fontSize = descriptionSize(),
                    textAlign = TextAlign.Justify
                )
            }

            LazyColumn(modifier = modifier.weight(1f)) {
                items(availableCards!!.size) { index ->
                    val itemCard = availableCards[index]
                    ItemCard(
                        title = itemCard.name,
                        description = itemCard.description,
                        alpha = if (selectedCardId == null || selectedCardId == itemCard.id) 1f else .5f,
                        onClickItem = {
                            selectedCardId = itemCard.id
                        }
                    )
                }
            }

            Row(modifier = Modifier.padding(top = paddingTop())) {
                Button(
                    modifier = Modifier
                        .padding(end = 5.sdp)
                        .height(heightButton())
                        .weight(1f)
                        .alpha(if (selectedCardId == null) 0.5f else 1f), // تغییر آلفا بر اساس مقدار idCard,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        disabledContainerColor = Color.White
                    ),
                    enabled = selectedCardId != null,
                    onClick = {
                        if (whichTeamHasGoal == 0) {
                            onClickOk(selectedCardId!!, 1)
                        } else {
                            onClickOk(selectedCardId!!, 0)
                        }
                    }
                ) {
                    Text(
                        modifier = modifier.alpha(if (selectedCardId == null) 0.5f else 1f), // تغییر آلفا بر اساس مقدار idCard,,
                        text = stringResource(R.string.yes),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = FenceGreen
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .padding(start = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    border = BorderStroke(1.sdp, Color.White),
                    onClick = onDismiss
                ) {
                    Text(
                        text = stringResource(R.string.cansel),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    alpha: Float = 1f,
    onClickItem: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            modifier = modifier
                .padding(top = paddingRoundMini())
                .clickable {
                    onClickItem()
                }
                .alpha(alpha),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(sizeRound()),
            border = BorderStroke(width = 1.sdp, color = Color.White)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingRoundMini()),
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
                        text = title,
                        color = Color.White,
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaBold
                    )
                }
                Text(
                    text = description,
                    color = Color.White,
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium,
                    textAlign = TextAlign.Justify

                )
            }
        }
    }
}


@Composable
fun BottomSheetContentAboutUs(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onItemClick: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.about_us),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = { onDismiss() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.close_circle),
                        contentDescription = "btn_close",
                        modifier = modifier.size(20.sdp)
                    )
                }

            }

            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Text(
                modifier = modifier.padding(top = paddingTop(), bottom = paddingTopMedium()),
                text = stringResource(R.string.description_about_us),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )

            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { onItemClick() },
                colors = CardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                shape = RoundedCornerShape(sizeRound()),
                border = BorderStroke(width = 1.sdp, color = Color.White)
            ) {
                Row(
                    modifier = modifier
                        .padding(paddingRound())
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.sdp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.email),
                        contentDescription = "email",
                        modifier = modifier.size(sizePicSmall())
                    )
                    Column(modifier = modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.contact_support),
                            color = Color.White,
                            fontFamily = FontPeydaMedium,
                            fontSize = descriptionSize(),
                        )
                        Text(
                            modifier = modifier.padding(top = 4.sdp),
                            text = stringResource(R.string.description_contact_support),
                            color = Color.White,
                            fontFamily = FontPeydaMedium,
                            fontSize = 10.ssp,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun BottomSheetResultOfThisRound(
    modifier: Modifier = Modifier,
    whichTeamResult: Int,
    onClickItem: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    val listResult: List<ResultThisRoundModel> =
        if (whichTeamResult == 0) {
            listOf(
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.cup,
                    text = stringResource(R.string.first_team_scored_a_single_goal)
                ),
                ResultThisRoundModel(
                    id = 1,
                    image = R.drawable.tick,
                    text = stringResource(R.string.first_team_scored)
                ),
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.square_cross,
                    text = stringResource(R.string.first_team_did_not_score)
                )
            )
        } else {
            listOf(
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.cup,
                    text = stringResource(R.string.second_team_scored_a_single_goal)
                ),
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.tick,
                    text = stringResource(R.string.second_team_scored)
                ),
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.square_cross,
                    text = stringResource(R.string.second_team_did_not_score)
                )
            )
        }
    val teamInfo = sharedViewModel.getTeam(whichTeamResult)
//    val teamInfo = if (whichTeamResult == 0) teams[0] else teams[1]

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.result_of_this_round),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
//                IconButton(
//                    onClick = { onDismiss() }
//                ) {
//                    Image(
//                        painter = painterResource(R.drawable.close_circle),
//                        contentDescription = "btn_close",
//                        modifier = modifier.size(20.sdp)
//                    )
//                }
            }
            HorizontalDivider(
                modifier = modifier.padding(
                    top = paddingTop(),
                    bottom = paddingTop()
                )
            )

            ItemResult(modifier = modifier, item = listResult[0], onClickItem = {
                if (whichTeamResult == 0) {
                    if (teamInfo!!.selectedCube) {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateScoreTeam(
                            teamId = 0,
                            newScore = Utils.WHICH_SELECT_NUMBER_CUBE
                        )

                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = false)
                        }
                    } else {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateScoreTeam(teamId = 0, newScore = 2)

                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = false)
                        }
                    }
                } else if (whichTeamResult == 1) {
                    if (teamInfo!!.selectedCube) {
                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateScoreTeam(
                            teamId = 1,
                            newScore = Utils.WHICH_SELECT_NUMBER_CUBE
                        )

                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = false)
                        }
                    } else {
                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateScoreTeam(teamId = 1, newScore = 2)

                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = false)
                        }
                    }
                }
                onClickItem(whichTeamResult)
                sharedViewModel.updateTeam(teamId = whichTeamResult) {
                    copy(selectedCube = false)
                }
            })
            ItemResult(modifier = modifier, item = listResult[1], onClickItem = {
                if (whichTeamResult == 1) {
                    if (teamInfo!!.selectedCube) {
                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = false)
                        }
                        sharedViewModel.updateScoreTeam(
                            teamId = 1,
                            newScore = Utils.WHICH_SELECT_NUMBER_CUBE
                        )
                    } else {
                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = false)
                        }
                    }

                } else if (whichTeamResult == 0) {
                    if (teamInfo!!.selectedCube) {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = false)
                        }
                        sharedViewModel.updateScoreTeam(
                            teamId = 0,
                            newScore = Utils.WHICH_SELECT_NUMBER_CUBE
                        )
                    } else {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = false)
                        }
                    }
                }
                onClickItem(whichTeamResult)
                sharedViewModel.updateTeam(teamId = whichTeamResult) {
                    copy(selectedCube = false)
                }
            })
            ItemResult(modifier = modifier, item = listResult[2], onClickItem = {
                if (whichTeamResult == 0) {
                    if (teamInfo!!.selectedCube) {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = false)
                        }
                        sharedViewModel.updateScoreTeam(
                            teamId = 1,
                            newScore = Utils.WHICH_SELECT_NUMBER_CUBE
                        )
                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = true)
                        }
                    } else {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = false)
                        }
                        sharedViewModel.updateScoreTeam(teamId = 1, newScore = 1)

                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = true)
                        }
                    }
                } else if (whichTeamResult == 1) {
                    if (teamInfo!!.selectedCube) {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateScoreTeam(
                            teamId = 0,
                            newScore = Utils.WHICH_SELECT_NUMBER_CUBE
                        )

                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = false)
                        }
                    } else {
                        sharedViewModel.updateTeam(teamId = 0) {
                            copy(hasGoal = true)
                        }
                        sharedViewModel.updateScoreTeam(teamId = 0, newScore = 1)

                        sharedViewModel.updateTeam(teamId = 1) {
                            copy(hasGoal = false)
                        }
                    }
                }
                onClickItem(whichTeamResult)
                sharedViewModel.updateTeam(teamId = whichTeamResult) {
                    copy(selectedCube = false)
                }
            })
        }
    }
}

@Composable
fun BottomSheetResultShahGoal(
    modifier: Modifier = Modifier,
    whichTeamResult: Int,
    onClickItem: (Boolean) -> Unit,
) {
    val listResult: List<ResultThisRoundModel> =
        if (whichTeamResult == 0) {
            listOf(
                ResultThisRoundModel(
                    id = 1,
                    image = R.drawable.tick,
                    text = stringResource(R.string.first_team_scored_shah_goal)
                ),
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.square_cross,
                    text = stringResource(R.string.first_team_did_not_scored_shah_goal)
                )
            )
        } else {
            listOf(
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.tick,
                    text = stringResource(R.string.second_team_scored_shah_goal)
                ),
                ResultThisRoundModel(
                    id = 0,
                    image = R.drawable.square_cross,
                    text = stringResource(R.string.second_team_did_not_scored_shah_goal)
                )
            )
        }
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.result_of_shah_goal),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
            }
            HorizontalDivider(
                modifier = modifier.padding(
                    top = paddingTop(),
                    bottom = paddingTop()
                )
            )

            ItemResult(modifier = modifier,
                item = listResult[0],
                onClickItem = { onClickItem(true) }
            )
            ItemResult(modifier = modifier,
                item = listResult[1],
                onClickItem = { onClickItem(false) }
            )
        }
    }
}

@Composable
fun ItemResult(
    modifier: Modifier,
    item: ResultThisRoundModel,
    onClickItem: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(top = paddingTop())
            .border(1.sdp, Color.White, RoundedCornerShape(sizeRound()))
            .clip(RoundedCornerShape(sizeRound()))
            .fillMaxWidth()
            .clickable { onClickItem() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(paddingTopMedium())
                .size(sizePicSmall()),
            painter = painterResource(id = item.image),
            contentDescription = null
        )

        Text(
            text = item.text,
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun BottomSheetContactTheOpeningDuelOfTheGame(
    modifier: Modifier = Modifier,
    whichTeamHasGoal: Int,
    onClickItem: (Int) -> Unit,
    onDismissRequest: () -> Unit // مدیریت بسته شدن Bottom Sheet
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.note),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = modifier.padding(end = 5.sdp)
                )
                Text(
                    text = stringResource(R.string.opening_duel),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
            }
            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Text(
                modifier = modifier.padding(top = paddingTop(), bottom = paddingTopMedium()),
                text = stringResource(R.string.description_opening_duel),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )
            Row(
                modifier = modifier
                    .padding(bottom = 8.sdp)
                    .border(1.sdp, Color.White, RoundedCornerShape(sizeRound()))
                    .clip(RoundedCornerShape(sizeRound()))
                    .fillMaxWidth()
                    .clickable {
                        if (whichTeamHasGoal == 0) {
                            onClickItem(0)
                        } else {
                            onClickItem(1)
                        }
                        onDismissRequest()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(paddingRound())
                        .size(sizePicMedium()),
                    painter = if (whichTeamHasGoal == 0)
                        painterResource(id = R.drawable.pic_team_one)
                    else
                        painterResource(R.drawable.pic_team_two),
                    contentDescription = null
                )

                Text(
                    text = if (whichTeamHasGoal == 0)
                        stringResource(R.string.first_team_kept_goal_)
                    else
                        stringResource(R.string.second_team_kept_goal_),
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )
            }

            Row(
                modifier = modifier
                    .border(1.sdp, Color.White, RoundedCornerShape(sizeRound()))
                    .clip(RoundedCornerShape(sizeRound()))
                    .fillMaxWidth()
                    .clickable {
                        if (whichTeamHasGoal == 0) {
                            onClickItem(1)
                        } else {
                            onClickItem(0)
                        }
                        onDismissRequest()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(paddingRound())
                        .size(sizePicMedium()),
                    painter = if (whichTeamHasGoal == 0)
                        painterResource(id = R.drawable.pic_team_two)
                    else
                        painterResource(R.drawable.pic_team_one),
                    contentDescription = null
                )
                Text(
                    text = if (whichTeamHasGoal == 0)
                        stringResource(R.string.second_team_scored)
                    else
                        stringResource(R.string.first_team_scored),
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }

}

@Composable
fun BottomSheetContactResultDuel(
    modifier: Modifier = Modifier,
    onClickItemUp: (Int) -> Unit,
    onClickItemDown: (Int) -> Unit,
    onDismissRequest: () -> Unit, // مدیریت بسته شدن Bottom Sheet
    whichTeamHasGoal: Int
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.note),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = modifier.padding(end = 5.sdp)
                )
                Text(
                    text = stringResource(R.string.result_duel),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
            }
            HorizontalDivider(
                modifier = modifier.padding(
                    top = paddingTop(),
                    bottom = paddingTopMedium()
                )
            )

            Row(
                modifier = modifier
                    .padding(bottom = 8.sdp)
                    .border(1.sdp, Color.White, RoundedCornerShape(sizeRound()))
                    .clip(RoundedCornerShape(sizeRound()))
                    .fillMaxWidth()
                    .clickable {
                        onClickItemUp(whichTeamHasGoal)
                        onDismissRequest()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(paddingRound())
                        .size(sizePicMedium()),
                    painter = if (whichTeamHasGoal == 0) painterResource(id = R.drawable.pic_team_one)
                    else painterResource(R.drawable.pic_team_two),
                    contentDescription = null
                )

                Text(
                    text = if (whichTeamHasGoal == 0) "تیم اول گل را حفظ کرد."
                    else "تیم دوم گل را حفظ کرد.",
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )
            }

            Row(
                modifier = modifier
                    .border(1.sdp, Color.White, RoundedCornerShape(sizeRound()))
                    .clip(RoundedCornerShape(sizeRound()))
                    .fillMaxWidth()
                    .clickable {
                        onClickItemDown(whichTeamHasGoal)
                        onDismissRequest()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(paddingRound())
                        .size(sizePicMedium()),
                    painter = if (whichTeamHasGoal == 0) painterResource(id = R.drawable.pic_team_one)
                    else painterResource(R.drawable.pic_team_two),
                    contentDescription = null
                )
                Text(
                    text = if (whichTeamHasGoal == 0) "تیم اول گل را از دست داد." else "تیم دوم گل را از دست داد.",
                    fontSize = descriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Composable
fun BottomSheetContactExitGame(
    modifier: Modifier = Modifier,
    onClickExit: () -> Unit,
    onClickContinueGame: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.exit),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = onClickContinueGame
                ) {
                    Image(
                        painter = painterResource(R.drawable.close_circle),
                        contentDescription = "btn_close",
                        modifier = modifier.size(20.sdp)
                    )
                }

            }
            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Text(
                modifier = modifier.padding(top = paddingTop(), bottom = paddingTopMedium()),
                text = stringResource(R.string.description_exit_game),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )

            Row(modifier = Modifier.padding(top = paddingTopLarge(), bottom = paddingRound())) {
                Button(
                    modifier = Modifier
                        .padding(start = paddingRound(), end = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = FenceGreen
                    ),
                    onClick = { onClickContinueGame() }
                ) {
                    Text(
                        text = stringResource(R.string.continue_game),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = FenceGreen
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .padding(end = paddingRound(), start = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    border = BorderStroke(1.sdp, Color.White),
                    onClick = onClickExit
                ) {
                    Text(
                        text = stringResource(R.string.exit),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BottomSheetContactExitApp(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onClickStar: () -> Unit,
    onClickExit: () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.exit),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = { onDismiss() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.close_circle),
                        contentDescription = "btn_close",
                        modifier = modifier.size(20.sdp)
                    )
                }

            }
            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Text(
                modifier = modifier.padding(top = paddingTop(), bottom = paddingTopMedium()),
                text = stringResource(R.string.description_exit_app),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )
            Row(
                modifier = modifier
                    .padding(
                        top = paddingTop(),
                        start = paddingTopMedium(),
                        end = paddingTopMedium()
                    )
                    .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.sdp)
            ) {
                Card(
                    modifier = modifier
                        .weight(1f)
                        .clickable { onClickStar() }
                        .align(Alignment.CenterVertically),
                    colors = CardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(sizeRound()),
                    border = BorderStroke(width = 1.sdp, color = Color.White)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(paddingTopMedium()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.star),
                            contentDescription = "star",
                            modifier = modifier.size(sizePicMedium())
                        )
                        Text(
                            modifier = modifier.padding(
                                top = paddingTopMedium()
                            ),
                            text = stringResource(R.string.rate),
                            color = Color.White,
                            fontFamily = FontPeydaMedium,
                            fontSize = descriptionSize(),
                            textAlign = TextAlign.Justify
                        )
                    }
                }

                Card(
                    modifier = modifier
                        .weight(1f)
                        .clickable { onClickExit() },
                    colors = CardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(sizeRound()),
                    border = BorderStroke(width = 1.sdp, color = Color.White)
                ) {
                    Column(
                        modifier = modifier
                            .padding(paddingTopMedium())
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.exit),
                            contentDescription = "star",
                            modifier = modifier.size(sizePicMedium())
                        )
                        Text(
                            modifier = modifier.padding(
                                top = paddingTopMedium(),
                            ),
                            text = stringResource(R.string.exit),
                            color = Color.White,
                            fontFamily = FontPeydaMedium,
                            fontSize = descriptionSize(),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetContactApps(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    val listApps = listOf(
        AppModel(
            id = 0,
            name = stringResource(R.string.open_chest),
            description = stringResource(R.string.description_open_chest),
            image = R.drawable.pic_open_chest
        ),
        AppModel(
            id = 1,
            name = stringResource(R.string.pro_wallpaper),
            description = stringResource(R.string.description_pro_wallpaper),
            image = R.drawable.pic_pro_wallpaper
        ),
        AppModel(
            id = 2,
            name = stringResource(R.string.intelligence_test),
            description = stringResource(R.string.description_intelligence_test),
            image = R.drawable.pic_intelligence_test
        ),
        AppModel(
            id = 3,
            name = stringResource(R.string.check_list),
            description = stringResource(R.string.description_check_list),
            image = R.drawable.pic_check_list
        )
    )
    val context = LocalContext.current

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound(), bottom = paddingRound())
                .fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.apps),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(
                    onClick = { onDismiss() }
                ) {
                    Image(
                        painter = painterResource(R.drawable.close_circle),
                        contentDescription = "btn_close",
                        modifier = modifier.size(20.sdp)
                    )
                }

            }
            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Text(
                modifier = modifier.padding(top = paddingTop(), bottom = paddingTop()),
                text = stringResource(R.string.description_apps),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )
            listApps.forEach { item ->
                ItemApps(item = item, onClickItem = {
                    when (item.id) {
                        0 -> {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data =
                                    Uri.parse("https://cafebazaar.ir/app/ir.developre.chistangame")
                            }
                            context.startActivity(intent)
                        }

                        1 -> {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data =
                                    Uri.parse("https://cafebazaar.ir/app/ir.forrtestt.wall1")
                            }
                            context.startActivity(intent)
                        }

                        2 -> {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data =
                                    Uri.parse("https://cafebazaar.ir/app/com.example.challenginquestions")
                            }
                            context.startActivity(intent)
                        }

                        3 -> {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data =
                                    Uri.parse("https://cafebazaar.ir/app/ir.developer.todolist")
                            }
                            context.startActivity(intent)
                        }
                    }
                })
            }
        }
    }
}

@Composable
fun ItemApps(modifier: Modifier = Modifier, item: AppModel, onClickItem: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = paddingTop())
            .clickable { onClickItem() },
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        shape = RoundedCornerShape(sizeRound()),
        border = BorderStroke(width = 1.sdp, color = Color.White)
    ) {
        Row(
            modifier = modifier
                .padding(paddingRound())
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.sdp)
        ) {
            Image(
                painter = painterResource(item.image),
                contentDescription = item.name,
                modifier = modifier.size(sizePicMedium()),
                contentScale = ContentScale.Crop
            )
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = item.name,
                    color = Color.White,
                    fontFamily = FontPeydaMedium,
                    fontSize = descriptionSize(),
                )
                Text(
                    modifier = modifier.padding(top = 4.sdp),
                    text = item.description,
                    color = Color.White,
                    fontFamily = FontPeydaMedium,
                    fontSize = 10.ssp,
                    textAlign = TextAlign.Justify
                )
            }
            Image(
                painter = painterResource(R.drawable.file_download),
                contentDescription = "email",
                modifier = modifier.size(sizeIcon()),
            )
        }
    }
}

@Composable
fun BottomSheetWinner(
    modifier: Modifier = Modifier,
    whichTeamHasGoal: Int,
    onClickRepeatGame: () -> Unit,
    onClickExit: () -> Unit
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.final_result),
                    color = Color.White,
                    fontFamily = FontPeydaBold,
                    fontSize = titleSize()
                )
            }
            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))

            Image(
                modifier = modifier
                    .padding(paddingTopMedium())
                    .size(sizePicMedium()),
                painter = if (whichTeamHasGoal == 0) painterResource(R.drawable.pic_team_one)
                else painterResource(R.drawable.pic_team_two),
                contentDescription = null
            )

            Text(
                modifier = modifier.padding(top = paddingTop(), bottom = paddingTopMedium()),
                text = if (whichTeamHasGoal == 0) stringResource(R.string.description_final_game_team_one)
                else stringResource(R.string.description_final_game_team_two),
                color = Color.White,
                fontFamily = FontPeydaMedium,
                fontSize = descriptionSize(),
                textAlign = TextAlign.Justify
            )

            Row(modifier = Modifier.padding(top = paddingTopLarge(), bottom = paddingRound())) {
                Button(
                    modifier = Modifier
                        .padding(end = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = FenceGreen
                    ),
                    onClick = { onClickRepeatGame() }
                ) {
                    Text(
                        text = stringResource(R.string.repeat_game),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = FenceGreen
                    )
                }

                OutlinedButton(
                    modifier = Modifier
                        .padding(start = 5.sdp)
                        .height(heightButton())
                        .weight(1f),
                    border = BorderStroke(1.sdp, Color.White),
                    onClick = onClickExit
                ) {
                    Text(
                        text = stringResource(R.string.exit),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun BottomSheetFinalResultPreview() {
    BottomSheetWinner(whichTeamHasGoal = 0, onClickRepeatGame = {}, onClickExit = {})
}

@Preview
@Composable
private fun BottomSheetCubePreview() {
    BottomSheetCube(whichTeamHasGoal = 0, onDismiss = {}, onConfirm = {})
}

@Preview
@Composable
private fun BottomSheetConfirmCubePreview() {
    BottomSheetConfirmCube(whichTeamHasGoal = 0, numberCube = 2, onConfirm = {}, onDismiss = {})
}

@Preview
@Composable
private fun TheOpeningDuelOfTheGamePreview() {
    BottomSheetContactTheOpeningDuelOfTheGame(
        whichTeamHasGoal = 1,
        onClickItem = {},
        onDismissRequest = {})
}

@Preview
@Composable
private fun BottomSheetContactResultDuelPreview() {
    BottomSheetContactResultDuel(
        onClickItemUp = {},
        onClickItemDown = {},
        onDismissRequest = {},
        whichTeamHasGoal = 1
    )
}

@Preview
@Composable
private fun BottomSheetContentPreview() {
    BottomSheetContentAboutUs(
        onDismiss = {},
        onItemClick = {}
    )
}

@Preview
@Composable
private fun BottomSheetContactAppsPreview() {
    BottomSheetContactApps(
        onDismiss = {}
    )
}

@Preview
@Composable
private fun ExitAppPreview() {
    BottomSheetContactExitGame(
        onClickExit = {},
        onClickContinueGame = {}
    )
}

@Preview
@Composable
private fun ExitGamePreview() {
    BottomSheetContactExitApp(
        onDismiss = {},
        onClickStar = {},
        onClickExit = {}
    )
}


