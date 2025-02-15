package ir.developer.goalorpooch_compose.ui.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.developer.goalorpooch_compose.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    private var mediaPlayer: MediaPlayer? = null

    // برای به‌روزرسانی آیکون به StateFlow نیاز داریم
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    fun playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.music).apply {
                isLooping = true
            }

//            mediaPlayer?.setOnCompletionListener {
//                _isPlaying.value = false
//            }
        }
        mediaPlayer?.start()
        _isPlaying.value = true
    }

    fun stopMusic() {
        mediaPlayer?.pause()
        mediaPlayer?.seekTo(0)
        _isPlaying.value = false
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}