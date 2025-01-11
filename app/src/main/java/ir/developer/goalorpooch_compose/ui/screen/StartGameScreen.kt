package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium_two
import ir.developer.goalorpooch_compose.ui.theme.sizePicSmall
import ir.developer.goalorpooch_compose.ui.theme.sizePicVerySmall
import ir.developer.goalorpooch_compose.ui.theme.sizeRound
import ir.developer.goalorpooch_compose.ui.theme.titleSize
import ir.kaaveh.sdpcompose.sdp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartGameScreen(modifier: Modifier = Modifier) {
    Scaffold { innerPadding ->
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(R.drawable.main_background),
                        contentScale = ContentScale.Crop
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderGame()
                TableGame()

//time
                Button(
                    modifier = modifier.width(130.sdp).height(50.sdp),
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
fun HeaderGame(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.sdp)
            .padding(paddingRound())
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
            text = "8",
            fontSize = descriptionSize(),
            fontFamily = FontPeydaBold,
            color = Color.White
        )
        VerticalDivider(modifier.height(25.sdp), thickness = 2.sdp)
        Text(
            text = "5",
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

@Preview
@Composable
private fun StartGameScreenPreview() {
    StartGameScreen()
}