package ir.developer.goalorpooch_compose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.FontPeydaBold
import ir.developer.goalorpooch_compose.ui.theme.paddingRound
import ir.developer.goalorpooch_compose.ui.theme.paddingTop
import ir.developer.goalorpooch_compose.ui.theme.titleSize

@Composable
fun AppBar(title:String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
    Row(
        modifier = Modifier
            .padding(top = paddingRound(), start = paddingRound(), end = paddingRound())
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontSize = titleSize(),
            fontFamily = FontPeydaBold,
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { }) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "back",
                tint = Color.White
            )
        }
    }

    HorizontalDivider(
        modifier = Modifier.padding(
            top = paddingTop(),
            start = paddingRound(),
            end = paddingRound()
        )
    )
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    AppBar(stringResource(R.string.guide))
}