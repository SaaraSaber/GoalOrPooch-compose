package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.network.CheckNetwork
import ir.developer.goalorpooch_compose.tapsell.Tapsell
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.HihadaBrown
import ir.developer.goalorpooch_compose.ui.theme.fontSizeButton
import ir.developer.goalorpooch_compose.ui.theme.heightButton
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundBottomSheet
import ir.developer.goalorpooch_compose.ui.theme.sizeRoundMax
import ir.developer.goalorpooch_compose.ui.theme.widthButton
import ir.developer.goalorpooch_compose.util.Utils
import ir.kaaveh.sdpcompose.sdp
import ir.tapsell.plus.AdRequestCallback
import ir.tapsell.plus.TapsellPlus
import ir.tapsell.plus.TapsellPlusBannerType
import ir.tapsell.plus.model.TapsellPlusAdModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "QueryPermissionsNeeded")
@Composable
fun HomeScreen(
    navController: NavController,
//    viewModelMusic: MusicPlayerViewModel,
    tapsell: Tapsell
) {
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

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .paint(
                            painterResource(id = R.drawable.main_background),
                            contentScale = ContentScale.Crop
                        )
                ) {
                    // محتوای صفحه
                    HomeScreenContent(navController
//                        , viewModelMusic
                    )
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
fun HomeScreenContent(navController: NavController
//                      , viewModelMusic: MusicPlayerViewModel
) {
    var showBottomSheetAboutUs by remember { mutableStateOf(false) }
    var showBottomSheetApps by remember { mutableStateOf(false) }
    var showBottomSheetExit by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val activity = context as? Activity
    var showToast by remember { mutableStateOf(false) }
    val versionName = getVersionName(context)

    // مدیریت دکمه برگشت
    BackHandler(enabled = !showBottomSheetExit) {
        showBottomSheetExit = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
//            Row(
//                modifier = Modifier
//                    .padding(paddingRound())
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.End
//            ) { MusicControlButton(viewModel = viewModelMusic) }

            Image(
                modifier = Modifier
                    .padding(paddingTopLarge())
                    .size(140.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.logo), contentDescription = "logo"
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .width(widthButton())
                    .height(heightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(sizeRoundMax()),
                contentPadding = PaddingValues(0.dp),
                onClick = { navController.navigate(Utils.SETTING_SCREEN) }
            ) {
                Text(
                    text = stringResource(R.string.start),
                    fontSize = fontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = paddingTop())
                    .width(widthButton())
                    .height(heightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(sizeRoundMax()),
                contentPadding = PaddingValues(0.dp),
                onClick = { navController.navigate(Utils.GAME_GUIDE_SCREEN) }) {
                Text(
                    text = stringResource(R.string.guide),
                    fontSize = fontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = paddingTop())
                    .width(widthButton())
                    .height(heightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(sizeRoundMax()),
                contentPadding = PaddingValues(0.dp),
                onClick = { showBottomSheetAboutUs = true }) {
                Text(
                    text = stringResource(R.string.about_us),
                    fontSize = fontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = paddingTop())
                    .width(widthButton())
                    .height(heightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(sizeRoundMax()),
                contentPadding = PaddingValues(0.dp),
                onClick = { showBottomSheetApps = true }) {
                Text(
                    text = stringResource(R.string.apps),
                    fontSize = fontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .padding(bottom = paddingRound())
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.version_app, versionName),
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = FontPeydaBold
            )

            if (showBottomSheetAboutUs) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheetAboutUs = false },
                    sheetState = sheetState,
                    shape = RoundedCornerShape(sizeRoundBottomSheet()),
                    containerColor = FenceGreen
                ) {
                    BottomSheetContentAboutUs(
                        onDismiss = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion { showBottomSheetAboutUs = false }
                        },
                        onItemClick = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = Uri.parse("mailto:")
                                putExtra(
                                    Intent.EXTRA_EMAIL,
                                    arrayOf(context.getString(R.string.address_email))
                                )
                                putExtra(
                                    Intent.EXTRA_SUBJECT,
                                    context.getString(R.string.support)
                                )
                            }
                            context.startActivity(intent)

                        }
                    )
                }
            }

            if (showBottomSheetApps) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheetApps = false },
                    sheetState = sheetState,
                    shape = RoundedCornerShape(sizeRoundBottomSheet()),
                    containerColor = FenceGreen
                ) {
                    BottomSheetContactApps(
                        onShowToast = {
                            showToast = true
                        },
                        onDismiss = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion { showBottomSheetApps = false }
                        }
                    )
                }
            }

            if (showBottomSheetExit) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheetExit = false },
                    sheetState = sheetState,
                    shape = RoundedCornerShape(sizeRoundBottomSheet()),
                    containerColor = FenceGreen,
                    properties = ModalBottomSheetProperties(
                        securePolicy = SecureFlagPolicy.SecureOff,
                        shouldDismissOnBackPress = false
                    )
                ) {
                    // اینجا بک هندلر موقعی که شیت بازه
                    BackHandler {
                        activity?.finishAffinity()
                    }

                    BottomSheetContactExitApp(
                        onDismiss = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion { showBottomSheetExit = false }
                        },
                        onClickStar = {
                            val intent = Intent(Intent.ACTION_EDIT)
                            intent.setData(Uri.parse("bazaar://details?id=" + Utils.PACKAGE_NAME))
                            intent.setPackage("com.farsitel.bazaar")
                            context.startActivity(intent)
                        },
                        onClickExit = { activity?.finishAffinity() }
                    )
                }
            }
        }

//  نمایش Toast به‌صورت جداگانه
        if (showToast) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                CustomToast(
                    message = stringResource(R.string.message_catch),
                    isVisible = true,
                    color = R.color.yellow,
                    icon = R.drawable.danger_circle,
                    onDismiss = { showToast = false }
                )
            }
        }
    }
}

fun getVersionName(context: Context): String {
    return context.packageManager.getPackageInfo(
        context.packageName, 0
    ).versionName.toString()
}

@Composable
fun TapsellBannerAd(standardBannerResponseId: String, tapsell: Tapsell) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        Arrangement.SpaceAround,
        Alignment.CenterHorizontally
    ) {
        AndroidView(
            factory = { ctx ->
                FrameLayout(ctx).apply {
                    val bannerView = FrameLayout(ctx)
                    addView(bannerView)

                    // نمایش تبلیغ در View
                    tapsell.showStandardBannerAd(standardBannerResponseId, bannerView)
                }
            },
            modifier = Modifier
//                .width(270.sdp)
                .wrapContentWidth()
                .height(70.sdp) // ارتفاع تبلیغ بنری
        )
    }
}

