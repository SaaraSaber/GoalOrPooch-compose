package ir.developer.goalorpooch_compose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.model.GameGuideModel
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaMedium
import ir.developer.goalorpooch_compose.ui.theme.descriptionSize
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.paddingTopMedium
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameGuideScreen(modifier: Modifier = Modifier, sharedViewModel: SharedViewModel) {
    val item = remember { sharedViewModel.getItemsGameGuide() }

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
                AppBar("راهنما")

                LazyColumn(
                    modifier = modifier
                        .padding(
                            top = paddingTop(),
                            start = paddingRound(),
                            end = paddingRound(),
                            bottom = paddingRound()
                        )
                        .fillMaxSize()
                ) {
                    items(item.size) { index ->
                        val items = item[index]
                        ItemGameGuide(
                            item = items,
                            modifier = modifier,
                            isLastItem = index == item.size - 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemGameGuide(item: GameGuideModel, modifier: Modifier, isLastItem: Boolean) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(5.sdp)) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = paddingTop()),
            text = item.title,
            color = Color.White,
            fontSize = 18.ssp,
            fontFamily = FontPeydaBold
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = paddingTop()),
            text = item.description,
            color = Color.White,
            fontSize = descriptionSize(),
            fontFamily = FontPeydaMedium,
            textAlign = TextAlign.Justify
        )
        if (!isLastItem) {
            HorizontalDivider(
                modifier = modifier.padding(
                    top = paddingTopMedium(),
                    bottom = paddingTop(),
                    start = 50.sdp,
                    end = 50.sdp
                )
            )
        }

    }
}

//@Preview
//@Composable
//private fun GameGuideScreenPreview() {
//    GameGuideScreen()
//}