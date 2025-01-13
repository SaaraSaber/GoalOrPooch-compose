package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavController
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.model.GameGuideModel
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.sizeRound
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameGuideScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val item = remember { sharedViewModel.getItemsGameGuide() }
    var expandedItemIndex by remember { mutableIntStateOf(-1) } // نگهداری آیتم باز (index)

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(R.drawable.main_background),
                        contentScale = ContentScale.Crop
                    )
            ) {
                AppBar(
                    stringResource(R.string.guide),
                    showBackButton = true,
                    navController = navController
                )

                LazyColumn(
                    modifier = modifier
                        .padding(
                            top = paddingTop(),
                            start = paddingRound(),
                            end = paddingRound(),
                            bottom = paddingRound()
                        )
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.sdp)
                ) {
                    items(item.size) { index ->
                        val items = item[index]
                        ItemGameGuide(
                            item = items,
                            modifier = modifier,
                            isExpanded = expandedItemIndex == index, // بررسی اینکه این آیتم باز است یا خیر
                            onClick = {
                                expandedItemIndex =
                                    if (expandedItemIndex == index) -1 else index // باز یا بسته کردن
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemGameGuide(
    item: GameGuideModel,
    modifier: Modifier,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
//    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(sizeRound()),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(width = 1.sdp, color = Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingRound())
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable(
                        indication = null, // حذف Ripple
                        interactionSource = remember { MutableInteractionSource() } // جلوگیری از Highlight
                    ) { onClick() },// فراخوانی callback برای تغییر وضعیت
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 18.ssp,
                    fontFamily = FontPeydaMedium
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            // محتوای کارت (در حالت باز)
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(animationSpec = tween(durationMillis = 700)) + fadeIn(
                    animationSpec = tween(durationMillis = 700)
                ),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 700)) + fadeOut(
                    animationSpec = tween(durationMillis = 700)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = paddingTop())
                ) {
                    Text(
                        modifier = modifier
                            .fillMaxWidth(),
                        text = item.description,
                        color = Color.White,
                        fontSize = descriptionSize(),
                        fontFamily = FontPeydaMedium,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun GameGuideScreenPreview() {
//    GameGuideScreen()
//}