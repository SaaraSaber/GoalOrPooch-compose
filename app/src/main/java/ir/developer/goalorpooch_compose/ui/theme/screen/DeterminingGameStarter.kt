package ir.developer.goalorpooch_compose.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import ir.developer.goalorpooch_compose.Utils
import ir.developer.goalorpooch_compose.ui.theme.DescriptionSize
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.FontSizeButton
import ir.developer.goalorpooch_compose.ui.theme.PaddingRound
import ir.developer.goalorpooch_compose.ui.theme.PaddingTop
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.TitleSize
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch

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

                val items = listOf(
                    R.drawable.pic_team_one to "تیم اول بازی رو شروع می کند.",
                    R.drawable.pic_team_two to "تیم دوم بازی رو شروع می کند.",
                    R.drawable.pic_random_box to "انتخاب تصادفی آغازکننده"
                )
                items.forEach { (image, text) ->
                    ItemGameStarter(
                        image = image,
                        text = text,
                        paddingValues = innerPadding,
                        navController = navController
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemGameStarter(
    image: Int,
    text: String,
    paddingValues: PaddingValues,
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

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
            .background(color = FenceGreen)
            .clickable(
                enabled = true,
                onClick = {
                    showBottomSheet = true
                }
            ),
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
            contentDescription = "pic_team_one",
        )

        Text(
            text = text,
            fontSize = DescriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White,
            textAlign = TextAlign.Justify
        )

        if (showBottomSheet) {

            ModalBottomSheet(
                modifier = Modifier.padding(paddingValues),
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                containerColor = FenceGreen,
                tonalElevation = 16.sdp
            ) {
                // Sheet content

                BottomSheetContent(
                    onCardSelection = {
                        scope.launch {
                            sheetState.hide()
                            navController.navigate(Utils.SELECT_CARD_SCREEN)
                        }.invokeOnCompletion { showBottomSheet = false }
                    },
                    onDismiss = {
                        scope.launch { sheetState.hide() }
                            .invokeOnCompletion { showBottomSheet = false }
                    }
                )
            }
        }
    }

}

@Composable
fun BottomSheetContent(onCardSelection: () -> Unit, onDismiss: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(start = PaddingRound(), end = PaddingRound())
                .size(70.sdp),
            painter = painterResource(id = R.drawable.pic_team_one),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(
                top = PaddingTopMedium(),
                start = PaddingRound(),
                end = PaddingRound()
            ),
            text = "تیم اول بازی را شروع میکند.",
            fontSize = TitleSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White,
            textAlign = TextAlign.Justify
        )
        Row(modifier = Modifier.padding(top = PaddingTopLarge())) {
            Button(
                modifier = Modifier
                    .padding(top = PaddingTopMedium(), start = PaddingRound(), end = PaddingRound())
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = FenceGreen
                ),
                contentPadding = PaddingValues(8.sdp),
                onClick = onCardSelection
            ) {
                Text(
                    text = "انتخاب کارت",
                    fontSize = FontSizeButton(),
                    fontFamily = FontPeydaMedium,
                    color = FenceGreen
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .padding(top = PaddingTopMedium(), start = PaddingRound(), end = PaddingRound())
                    .weight(1f),
                border = BorderStroke(1.sdp, Color.White),
                contentPadding = PaddingValues(8.sdp),
                onClick = onDismiss
            ) {
                Text(
                    text = "انصراف",
                    fontSize = FontSizeButton(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun DeterminingGameStarterPreview() {
    val navController = rememberNavController()
    DeterminingGameStarter(navController)
}