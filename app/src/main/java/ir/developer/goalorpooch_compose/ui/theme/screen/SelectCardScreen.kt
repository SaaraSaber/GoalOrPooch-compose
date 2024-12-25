package ir.developer.goalorpooch_compose.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.DescriptionSize
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.PaddingRound
import ir.developer.goalorpooch_compose.ui.theme.PaddingTop
import ir.developer.goalorpooch_compose.ui.theme.SizePicMedium
import ir.kaaveh.sdpcompose.sdp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectCardScreen(navController: NavController) {
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
                AppBar(title = "توضیع کارت ها")
                Row(
                    modifier = Modifier.padding(
                        top = PaddingTop(),
                        start = PaddingRound(),
                        end = PaddingRound()
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(SizePicMedium()),
                        painter = painterResource(id = R.drawable.pic_team_one),
                        contentDescription = "pic_team_one"
                    )
                    Text(
                        modifier = Modifier.padding(start = PaddingRound()),
                        text = "تیم اول",
                        fontSize = DescriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )
                }

                Text(
                    modifier = Modifier.padding(
                        top = PaddingTop(),
                        start = PaddingRound(),
                        end = PaddingRound()
                    ),
                    text = "تیم اول بازی کارت های خود را انتخاب کند .شما باید 5 کارت از کارت های زیر را انتخاب کنید.",
                    fontSize = DescriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(4.sdp),
                    horizontalArrangement = Arrangement.spacedBy(2.sdp)
                ){
//                    items() { item ->
//                        PhotoItem(item)
//                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectCardScreenPreview() {
    val navController = rememberNavController()
    SelectCardScreen(navController = navController)
}