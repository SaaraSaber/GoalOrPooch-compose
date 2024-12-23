package ir.developer.goalorpooch_compose.ui.theme.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.FontSizeButton
import ir.developer.goalorpooch_compose.ui.theme.HeightButton
import ir.developer.goalorpooch_compose.ui.theme.HihadaBrown
import ir.developer.goalorpooch_compose.ui.theme.PaddingRound
import ir.developer.goalorpooch_compose.ui.theme.PaddingTop
import ir.developer.goalorpooch_compose.ui.theme.PaddingTopLarge
import ir.developer.goalorpooch_compose.ui.theme.WidthButton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .paint(
                    painterResource(id = R.drawable.main_background),
                    contentScale = ContentScale.Crop
                )
        ) {

            Button(
                modifier = Modifier
                    .padding(PaddingRound())
                    .size(40.dp),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                shape = RoundedCornerShape(10f),
                contentPadding = PaddingValues(0.dp),
                onClick = { }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.volume_loud),
                    contentDescription = "volume_loud"
                )
            }

            Image(
                modifier = Modifier
                    .padding(PaddingTopLarge())
                    .size(140.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.logo), contentDescription = "logo"
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .width(WidthButton())
                    .height(HeightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(100f),
                contentPadding = PaddingValues(0.dp),
                onClick = { }) {
                Text(
                    text = "شروع",
                    fontSize = FontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = PaddingTop())
                    .width(WidthButton())
                    .height(HeightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(100f),
                contentPadding = PaddingValues(0.dp),
                onClick = { }) {
                Text(
                    text = "راهنما",
                    fontSize = FontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = PaddingTop())
                    .width(WidthButton())
                    .height(HeightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(100f),
                contentPadding = PaddingValues(0.dp),
                onClick = { }) {
                Text(
                    text = "درباره ما",
                    fontSize = FontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = PaddingTop())
                    .width(WidthButton())
                    .height(HeightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(100f),
                contentPadding = PaddingValues(0.dp),
                onClick = { }) {
                Text(
                    text = "برنامه ها",
                    fontSize = FontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(WidthButton())
                    .height(HeightButton())
                    .align(Alignment.CenterHorizontally),
                colors = ButtonColors(
                    containerColor = FenceGreen,
                    contentColor = Color.White,
                    disabledContainerColor = HihadaBrown,
                    disabledContentColor = HihadaBrown
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(100f),
                contentPadding = PaddingValues(0.dp),
                onClick = { }) {
                Text(
                    text = "خروج",
                    fontSize = FontSizeButton(),
                    fontFamily = FontPeydaBold
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(bottom = PaddingRound())
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                text = "version 1",
                color = Color.White,
                fontSize = 15.sp,
                fontFamily = FontPeydaBold
            )

        }

    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}