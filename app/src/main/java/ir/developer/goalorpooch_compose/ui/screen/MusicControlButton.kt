package ir.developer.goalorpooch_compose.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.ui.theme.FenceGreen
import ir.developer.goalorpooch_compose.ui.theme.HihadaBrown
import ir.developer.goalorpooch_compose.ui.theme.sizeRound
import ir.developer.goalorpooch_compose.ui.viewmodel.MusicPlayerViewModel

@Composable
fun MusicControlButton(viewModel: MusicPlayerViewModel) {
    val isPlaying by viewModel.isPlaying.collectAsStateWithLifecycle()
    val playIcon = painterResource(id = R.drawable.volume_loud)
    val pauseIcon = painterResource(R.drawable.no_volume_loud)

    Button(
        modifier = Modifier
            .size(40.dp),
        colors = ButtonColors(
            containerColor = FenceGreen,
            contentColor = Color.White,
            disabledContainerColor = HihadaBrown,
            disabledContentColor = HihadaBrown
        ),
        shape = RoundedCornerShape(sizeRound()),
        contentPadding = PaddingValues(0.dp),
        onClick = {
            if (isPlaying) {
                viewModel.stopMusic()
            } else {
                viewModel.playMusic()
            }
        }) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = if (!isPlaying) pauseIcon else playIcon,
            contentDescription = "volume_loud"
        )
    }
}