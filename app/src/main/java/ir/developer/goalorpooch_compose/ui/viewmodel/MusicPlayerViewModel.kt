package ir.developer.goalorpooch_compose.ui.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
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
) : ViewModel() ,DefaultLifecycleObserver{
    private var mediaPlayer: MediaPlayer? = null

    // کلید برای ذخیره در DataStore
    private val IS_PLAYING_KEY = booleanPreferencesKey("is_playing_key")
    private val IS_FIRST_TIME_KEY = booleanPreferencesKey("is_first_time_key")

    // برای به‌روزرسانی آیکون به StateFlow نیاز داریم
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private var shouldResumeMusic = false // برای تشخیص اینکه هنگام برگشت دوباره پخش کنه یا نه

    init {
        // وصل کردن ViewModel به کل پروسه اپلیکیشن
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        // هنگام ساخت ویو مدل مقدار ذخیره شده رو بخونیم
        viewModelScope.launch {
            val preferences = context.dataStore.data.first()
            val isPlaying = preferences[IS_PLAYING_KEY] ?: false
            val isFirstTime = preferences[IS_FIRST_TIME_KEY] ?: true

            if (isFirstTime) {
                playMusic()
                saveFirstTimeFlag()
            } else {
                _isPlaying.value = isPlaying
                if (isPlaying) {
                    playMusic()
                }
            }
        }
    }

    fun playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.music).apply {
                isLooping = true
            }
        }
        mediaPlayer?.start()
        updatePlayingState(true)
    }

    fun stopMusic() {
        mediaPlayer?.pause()
        mediaPlayer?.seekTo(0)
        updatePlayingState(false)
    }

    private fun updatePlayingState(isPlaying: Boolean) {
        _isPlaying.value = isPlaying

        // ذخیره در DataStore
        viewModelScope.launch {
            context.dataStore.edit { preference ->
                preference[IS_PLAYING_KEY] = isPlaying
            }
        }
    }

    private suspend fun saveFirstTimeFlag() {
        context.dataStore.edit { preferences ->
            preferences[IS_FIRST_TIME_KEY] = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        mediaPlayer?.release()
        mediaPlayer = null
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        // وقتی میریم بیرون، وضعیت فعلی رو ذخیره می‌کنیم
        if (ProcessLifecycleOwner.get().lifecycle.currentState == Lifecycle.State.CREATED) {
            shouldResumeMusic = _isPlaying.value
            mediaPlayer?.pause()
            _isPlaying.value = false
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (shouldResumeMusic) {
            playMusic()
        }
    }
}