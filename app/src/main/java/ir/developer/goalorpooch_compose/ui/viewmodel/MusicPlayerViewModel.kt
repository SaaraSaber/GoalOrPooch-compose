package ir.developer.goalorpooch_compose.ui.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.database.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel(), DefaultLifecycleObserver {

    private var mediaPlayer: MediaPlayer? = null
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private var lastPosition = 0  // ذخیره موقعیت پخش
    private var shouldResumeMusic = false
    private var isAdPlaying = false  // آیا تبلیغ در حال پخش است؟

    private val IS_PLAYING_KEY = booleanPreferencesKey("is_playing_key")

    private suspend fun saveMusicState(isPlaying: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_PLAYING_KEY] = isPlaying
        }
    }

    init {
//        // پخش موزیک از اول هنگام شروع اپلیکیشن
//        playMusic()
        // وصل کردن ViewModel به کل پروسه اپلیکیشن
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        // هنگام ساخت ویو مدل مقدار ذخیره شده رو بخونیم
        viewModelScope.launch {
            val preferences = context.dataStore.data.first()
            val isPlaying = preferences[IS_PLAYING_KEY] ?: false  // وضعیت موزیک رو می‌خونیم

            _isPlaying.value = isPlaying
            if (isPlaying) {
                playMusic()  // اگر موزیک پخش بوده، پخش می‌کنیم
            }
        }
    }

    fun playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.music).apply {
                isLooping = true
            }
        }
        mediaPlayer?.seekTo(lastPosition)  // ادامه از موقعیت قبلی
        mediaPlayer?.start()
        _isPlaying.value = true
    }

     fun stopMusic() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                lastPosition =
                    it.currentPosition ?: 0   // برای اینکه از اول شروع بشه، به صفر برمی‌گردونیم
            }
        }
        _isPlaying.value = false  // مطمئن بشو که وضعیت ذخیره می‌شه
         viewModelScope.launch {
             saveMusicState(false)  // وضعیت "قطع" موزیک رو ذخیره می‌کنیم
         }
    }

    fun setAdPlaying(isPlaying: Boolean) {
        isAdPlaying = isPlaying
        if (isAdPlaying) {
            shouldResumeMusic =
                _isPlaying.value  // ذخیره می‌کنیم که آیا موزیک در حال پخش بوده یا نه
            stopMusic() // وقتی تبلیغ شروع شد، موزیک متوقف بشه
        } else {
            if (shouldResumeMusic) {
                playMusic() // وقتی تبلیغ تموم شد، موزیک از همون لحظه‌ای که قطع شده ادامه پیدا کنه
            }
        }
    }

    fun handleAppBackground(isInBackground: Boolean) {
        if (isInBackground) {
            shouldResumeMusic = _isPlaying.value  // ذخیره کنیم که موزیک در حال پخش بوده یا نه
            mediaPlayer?.pause()
            _isPlaying.value = false
        } else {
            if (shouldResumeMusic) {
                mediaPlayer?.start()
                _isPlaying.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}