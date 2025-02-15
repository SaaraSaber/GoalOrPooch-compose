package ir.developer.goalorpooch_compose

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import ir.developer.goalorpooch_compose.navigation.Navigation
import ir.developer.goalorpooch_compose.ui.viewmodel.MusicPlayerViewModel
import ir.developer.goalorpooch_compose.ui.viewmodel.SharedViewModel

@AndroidEntryPoint
@SuppressLint("RestrictedApi")
class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    private val musicPlayerViewModel: MusicPlayerViewModel by viewModels()

    @SuppressLint("ResourceAsColor", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.statusBarColor = ContextCompat.getColor(this, R.color.hihadaBrown)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.hihadaBrown)

        insertCards()

        setContent {
//lock orientation
            val context = LocalContext.current
            (context as? Activity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

            Navigation(sharedViewModel = sharedViewModel, viewModelMusic = musicPlayerViewModel)
        }
    }

    private fun insertCards() {
        sharedViewModel.addCard(
            id = 0,
            name = ContextCompat.getString(this, R.string.free_stone_free_sparrow),
            description = ContextCompat.getString(
                this,
                R.string.description_free_stone_free_sparrow
            )
        )
        sharedViewModel.addCard(
            id = 1,
            name = ContextCompat.getString(this, R.string.big_ball),
            description = ContextCompat.getString(this, R.string.description_bid_ball)
        )
        sharedViewModel.addCard(
            id = 2,
            name = ContextCompat.getString(this, R.string.sound_ball),
            description = ContextCompat.getString(this, R.string.description_sound_ball)
        )
        sharedViewModel.addCard(
            id = 3,
            name = ContextCompat.getString(this, R.string.one_arrow_and_two_badges),
            description = ContextCompat.getString(
                this,
                R.string.description_one_arrow_and_two_badges
            )
        )
        sharedViewModel.addCard(
            id = 4,
            name = ContextCompat.getString(this, R.string.antenna),
            description = ContextCompat.getString(this, R.string.description_antenna)
        )
        sharedViewModel.addCard(
            id = 5,
            name = ContextCompat.getString(this, R.string.duel),
            description = ContextCompat.getString(this, R.string.description_duel)
        )
        sharedViewModel.addCard(
            id = 6,
            name = ContextCompat.getString(this, R.string.empty_game),
            description = ContextCompat.getString(this, R.string.description_empty_game)
        )
        sharedViewModel.addCard(
            id = 7,
            name = ContextCompat.getString(this, R.string.remove_one_hand),
            description = ContextCompat.getString(this, R.string.description_remove_one_hand)
        )
        sharedViewModel.addCard(
            id = 8,
            name = ContextCompat.getString(this, R.string.free_stone_free_sparrow),
            description = ContextCompat.getString(
                this,
                R.string.description_free_stone_free_sparrow
            )
        )
    }

}

