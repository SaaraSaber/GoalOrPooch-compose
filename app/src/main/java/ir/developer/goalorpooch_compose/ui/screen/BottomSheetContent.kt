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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.heightButton
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.sizeIcon
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicSmall
import ir.developer.goalorpooch_compose.ui.theme.sizeRound
import ir.developer.goalorpooch_compose.ui.theme.titleSize
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

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
fun ResultOfThisRound(
    modifier: Modifier = Modifier,
    whichTeamResult: Int,
    onClickExit: () -> Unit,
    onClickItem: (Int) -> Unit
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

    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        Column(
            modifier = modifier
                .padding(start = paddingRound(), end = paddingRound())
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
                IconButton(
                    onClick = onClickExit
                ) {
                    Image(
                        painter = painterResource(R.drawable.close_circle),
                        contentDescription = "btn_close",
                        modifier = modifier.size(20.sdp)
                    )
                }
            }
            HorizontalDivider(modifier = modifier.padding(top = paddingTop()))
            LazyColumn(
                modifier = modifier
                    .padding(top = paddingTopMedium())
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.sdp)
            ) {
                items(listResult.size) { index ->
                    val item = listResult[index]
                    ItemResult(
                        modifier = modifier,
                        item = item,
                        onClickItem = { onClickItem(whichTeamResult) })
                }
            }
        }
    }
}

@Composable
fun ItemResult(modifier: Modifier, item: ResultThisRoundModel, onClickItem: () -> Unit) {
    Row(
        modifier = modifier
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
                        onDismissRequest ()
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

@Preview
@Composable
private fun TheOpeningDuelOfTheGamePreview() {
    BottomSheetContactTheOpeningDuelOfTheGame(whichTeamHasGoal = 1, onClickItem = {}, onDismissRequest = {})
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

@Preview
@Composable
private fun ResultOfThisRoundPreview() {
    ResultOfThisRound(whichTeamResult = 0, onClickExit = {}, onClickItem = {})
}

