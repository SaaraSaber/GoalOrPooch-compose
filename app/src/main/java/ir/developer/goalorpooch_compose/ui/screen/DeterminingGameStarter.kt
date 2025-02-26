package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.model.ItemStarterModel
import ir.developer.goalorpooch_compose.network.CheckNetwork
import ir.developer.goalorpooch_compose.tapsell.Tapsell
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.heightButton
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.theme.sizePicLarge
import ir.developer.goalorpooch_compose.ui.theme.sizePicMedium
import ir.developer.goalorpooch_compose.ui.theme.sizeRound
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundBottomSheet
import ir.developer.goalorpooch_compose.ui.theme.titleSize
import ir.developer.goalorpooch_compose.util.Utils
import ir.kaaveh.sdpcompose.sdp
import ir.tapsell.plus.AdRequestCallback
import ir.tapsell.plus.TapsellPlus
import ir.tapsell.plus.TapsellPlusBannerType
import ir.tapsell.plus.model.TapsellPlusAdModel
import kotlinx.coroutines.launch
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DeterminingGameStarter(navController: NavController,tapsell: Tapsell) {
    val items = listOf(
        ItemStarterModel(0, R.drawable.pic_team_one, stringResource(R.string.start_team_one)),
        ItemStarterModel(1, R.drawable.pic_team_two, stringResource(R.string.start_team_two)),
        ItemStarterModel(2, R.drawable.pic_random_box, stringResource(R.string.start_random))
    )
    val context = LocalContext.current
    // وضعیت دریافت تبلیغ
    var bannerResponseId by remember { mutableStateOf<String?>(null) }

    // درخواست تبلیغ از تپسل
    LaunchedEffect(Unit) {
        if (CheckNetwork.isInternetAvailable(context = context)) {
            try {
                TapsellPlus.requestStandardBannerAd(
                    context as Activity,
                    Utils.TAPSELL_BANNER_KEY,
                    TapsellPlusBannerType.BANNER_320x50,
                    object : AdRequestCallback() {
                        override fun response(tapsellPlusAdModel: TapsellPlusAdModel) {
                            super.response(tapsellPlusAdModel)
                            bannerResponseId = tapsellPlusAdModel.responseId
                        }

                        override fun error(message: String?) {
                            Log.e("TapsellBanner", "Ad Request Error: $message")
                        }
                    }
                )
            } catch (t: Throwable) {
                Toast.makeText(context, "خطایی رخ داده است", Toast.LENGTH_LONG).show()
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box (modifier = Modifier.fillMaxSize()){
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
                        title = stringResource(R.string.determining_the_initiator),
                        showBackButton = true,
                        navController = navController
                    )

                    Text(
                        modifier = Modifier.padding(
                            end = paddingRound(),
                            start = paddingRound(),
                            top = paddingTop(),
                            bottom = 30.sdp
                        ),
                        text = stringResource(R.string.starter_description),
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        color = Color.White,
                        textAlign = TextAlign.Justify
                    )

                    items.forEach { item ->
                        ItemGameStarter(item, navController)
                    }
                }

                if (CheckNetwork.isInternetAvailable(context = context)) {
                    // تبلیغ در پایین صفحه
                    bannerResponseId?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(paddingRound())
                        ) {
                            TapsellBannerAd(it, tapsell)
                        }
                    }
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
            .padding(end = paddingRound(), start = paddingRound(), top = paddingTop())
            .border(1.sdp, Color.White, RoundedCornerShape(sizeRound()))
            .clip(RoundedCornerShape(sizeRound()))
            .fillMaxWidth()
            .background(FenceGreen)
            .clickable { showBottomSheet = true },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(paddingTopMedium())
                .size(sizePicMedium()),
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

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            shape = RoundedCornerShape(sizeRoundBottomSheet()),
            containerColor = FenceGreen
        ) {
            BottomSheetContent(
                item = item,
                onDismiss = {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { showBottomSheet = false }
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
            if (randomIndex == 0) R.drawable.pic_team_one
            else R.drawable.pic_team_two
        val selectedText =
            if (randomIndex == 0) stringResource(R.string.start_team_one)
            else stringResource(R.string.start_team_two)

        Image(
            modifier = Modifier
                .padding(paddingRound())
                .size(sizePicLarge()),
            painter = painterResource(id = selectedImage),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(paddingTopMedium()),
            text = selectedText,
            fontSize = titleSize(),
            fontFamily = FontPeydaMedium,
            color = Color.White,
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
                onClick = { onCardSelection(randomIndex) }
            ) {
                Text(
                    text = stringResource(R.string.choose_card),
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

//@Preview
//@Composable
//private fun DeterminingGameStarterPreview() {
//    val navController = rememberNavController()
//    DeterminingGameStarter(navController)
//}