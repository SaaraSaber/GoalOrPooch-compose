package ir.developer.goalorpooch_compose.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.DescriptionSize
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.PaddingRound
import ir.developer.goalorpooch_compose.ui.theme.PaddingTop
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopMedium
import ir.kaaveh.sdpcompose.sdp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DeterminingGameStarter(navController: NavController) {
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
                AppBar(title = "تعیین آغاز کننده")

                Text(
                    modifier = Modifier.padding(
                        top = PaddingTop(),
                        start = PaddingRound(),
                        end = PaddingRound(),
                        bottom = 30.sdp
                    ),
                    text = "به دو تیم تقسیم شوید و سپس تیم آغاز کننده بازی را مشخص کنید.",
                    fontSize = DescriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                ItemGameStarter(
                    image = R.drawable.pic_team_one,
                    text = "تیم اول بازی رو شروع می کند."
                )
                ItemGameStarter(
                    image = R.drawable.pic_team_two,
                    text = "تیم دوم بازی رو شروع می کند."
                )
                ItemGameStarter(
                    image = R.drawable.pic_random_box,
                    text = "انتخاب تصادفی آغازکننده"
                )
            }
        }
    }
}


@Composable
fun ItemGameStarter(image: Int, text: String) {
    Row(
        modifier = Modifier
            .padding(
                top = PaddingTop(),
                start = PaddingRound(),
                end = PaddingRound()
            )
            .border(
                width = 1.sdp,
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(color = FenceGreen),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(
                    top = PaddingTopMedium(),
                    start = PaddingTopMedium(),
                    end = PaddingRound(),
                    bottom = PaddingTopMedium()
                ),
            painter = painterResource(id = image),
            contentDescription = "pic_team_one"
        )

        Text(
            text = text,
            fontSize = DescriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview
@Composable
private fun DeterminingGameStarterPreview() {
    val navController = rememberNavController()
    DeterminingGameStarter(navController)
}