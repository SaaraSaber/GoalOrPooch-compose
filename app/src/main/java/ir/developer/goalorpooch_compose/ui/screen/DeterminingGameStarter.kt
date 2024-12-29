package ir.developer.goalorpooch_compose.ui.screen

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import ir.developer.goalorpooch_compose.model.ItemStarterModel
import ir.developer.goalorpooch_compose.ui.theme.DescriptionSize
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.FontSizeButton
import ir.developer.goalorpooch_compose.ui.theme.PaddingRound
import ir.developer.goalorpooch_compose.ui.theme.PaddingTop
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.SizePicLarge
import ir.developer.goalorpooch_compose.ui.theme.SizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.TitleSize
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.launch
import kotlin.random.Random

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

                val items = remember { mutableStateListOf<ItemStarterModel>() }
                items.add(
                    ItemStarterModel(
                        id = 0,
                        image = R.drawable.pic_team_one,
                        text = "تیم اول بازی رو شروع می کند."
                    )
                )
                items.add(
                    ItemStarterModel(
                        id = 1,
                        image = R.drawable.pic_team_two,
                        text = "تیم دوم بازی رو شروع می کند."
                    )
                )
                items.add(
                    ItemStarterModel(
                        id = 2,
                        image = R.drawable.pic_random_box,
                        text = "انتخاب تصادفی آغازکننده"
                    )
                )
                items.forEachIndexed { _, item ->
                    ItemGameStarter(
                        image = item.image,
                        text = item.text,
                        idItem = item.id,
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
    idItem: Int,
    paddingValues: PaddingValues,
    navController: NavController,
    text: String
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
                )
                .size(SizePicMedium()),
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
                    image = image,
                    text = text,
                    idItem = idItem,
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
fun BottomSheetContent(
    image: Int,
    text: String,
    idItem: Int,
    onDismiss: () -> Unit,
    onCardSelection: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (idItem == 2) {
            var randomItem by remember { mutableIntStateOf(-1) }
            randomItem = Random.nextInt(0, 2)

            Utils.STARTER_GAME = randomItem

            if (randomItem == 0) {
                Image(
                    modifier = Modifier
                        .padding(start = PaddingRound(), end = PaddingRound())
                        .size(SizePicLarge()),
                    painter = painterResource(id = R.drawable.pic_team_one),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(
                        top = PaddingTopMedium(),
                        start = PaddingRound(),
                        end = PaddingRound()
                    ),
                    text = "تیم اول بازی رو شروع می کند.",
                    fontSize = TitleSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )
            } else {
                Image(
                    modifier = Modifier
                        .padding(start = PaddingRound(), end = PaddingRound())
                        .size(SizePicLarge()),
                    painter = painterResource(id = R.drawable.pic_team_two),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(
                        top = PaddingTopMedium(),
                        start = PaddingRound(),
                        end = PaddingRound()
                    ),
                    text = "تیم دوم بازی رو شروع می کند.",
                    fontSize = TitleSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )
            }
        } else {
            Utils.STARTER_GAME = idItem
            Image(
                modifier = Modifier
                    .padding(start = PaddingRound(), end = PaddingRound())
                    .size(SizePicLarge()),
                painter = painterResource(id = image),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(
                    top = PaddingTopMedium(),
                    start = PaddingRound(),
                    end = PaddingRound()
                ),
                text = text,
                fontSize = TitleSize(),
                fontFamily = FontPeydaMedium,
                color = Color.White,
                textAlign = TextAlign.Justify
            )
        }
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