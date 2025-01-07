package ir.developer.goalorpooch_compose.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.sizeIcon
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicSmall
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
                shape = RoundedCornerShape(10.sdp),
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
fun BottomSheetContactApps(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
//    onItemClick: () -> Unit
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
        shape = RoundedCornerShape(10.sdp),
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