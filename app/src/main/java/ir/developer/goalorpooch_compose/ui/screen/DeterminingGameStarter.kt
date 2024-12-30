package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
    val items = listOf(
        ItemStarterModel(0, R.drawable.pic_team_one, "تیم اول بازی رو شروع می کند."),
        ItemStarterModel(1, R.drawable.pic_team_two, "تیم دوم بازی رو شروع می کند."),
        ItemStarterModel(2, R.drawable.pic_random_box, "انتخاب تصادفی آغازکننده")
    )
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                        end = PaddingRound(),
                        start = PaddingRound(),
                        top = PaddingTop(),
                        bottom = 30.sdp
                    ),
                    text = "به دو تیم تقسیم شوید و سپس تیم آغاز کننده بازی را مشخص کنید.",
                    fontSize = DescriptionSize(),
                    fontFamily = FontPeydaMedium,
                    color = Color.White,
                    textAlign = TextAlign.Justify
                )

                items.forEach { item ->
                    ItemGameStarter(item, navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemGameStarter(item: ItemStarterModel, navController: NavController) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(end = PaddingRound(), start = PaddingRound(), top = PaddingTop())
            .border(1.sdp, Color.White, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(FenceGreen)
            .clickable { showBottomSheet = true },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(PaddingTopMedium())
                .size(SizePicMedium()),
            painter = painterResource(id = item.image),
            contentDescription = null
        )

        Text(
            text = item.text,
            fontSize = DescriptionSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White,
            textAlign = TextAlign.Justify
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            shape = RoundedCornerShape(16.dp),
            containerColor = FenceGreen
        ) {
            BottomSheetContent(item, onDismiss = {
                scope.launch { sheetState.hide() }.invokeOnCompletion { showBottomSheet = false }
            }, onCardSelection = { idItem ->
                scope.launch {
                    sheetState.hide()
                    navController.navigate("${Utils.SELECT_CARD_SCREEN}/${idItem}")
                }.invokeOnCompletion { showBottomSheet = false }
            })
        }
    }
}

@Composable
fun BottomSheetContent(
    item: ItemStarterModel,
    onDismiss: () -> Unit,
    onCardSelection: (Int) -> Unit
) {
    val randomIndex = if (item.id == 2) Random.nextInt(0, 2) else item.id
    Utils.STARTER_GAME = randomIndex

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val selectedImage =
            if (randomIndex == 0) R.drawable.pic_team_one else R.drawable.pic_team_two
        val selectedText =
            if (randomIndex == 0) "تیم اول بازی رو شروع می کند." else "تیم دوم بازی رو شروع می کند."

        Image(
            modifier = Modifier
                .padding(PaddingRound())
                .size(SizePicLarge()),
            painter = painterResource(id = selectedImage),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(PaddingTopMedium()),
            text = selectedText,
            fontSize = TitleSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White,
            textAlign = TextAlign.Justify
        )

        Row(modifier = Modifier.padding(top = PaddingTopLarge())) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(PaddingRound()),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = FenceGreen
                ),
                onClick = { onCardSelection(randomIndex) }
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
                    .weight(1f)
                    .padding(PaddingRound()),
                border = BorderStroke(1.sdp, Color.White),
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