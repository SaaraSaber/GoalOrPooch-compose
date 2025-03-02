package ir.developer.goalorpooch_compose.ui.viewmodel

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.database.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
//class MusicPlayerViewModel @Inject constructor(
//    @ApplicationContext private val context: Context
//) : ViewModel(), DefaultLifecycleObserver {
//
//    private var mediaPlayer: MediaPlayer? = null
//    private val _isPlaying = MutableStateFlow(false)
//    val isPlaying = _isPlaying.asStateFlow()
//
//    private var lastPosition = 0  // ذخیره موقعیت پخش
//    private var shouldResumeMusic = false
//    private var isAdPlaying = false  // آیا تبلیغ در حال پخش است؟
//
//    private val IS_PLAYING_KEY = booleanPreferencesKey("is_playing_key")
//
//    private suspend fun saveMusicState(isPlaying: Boolean) {
//        context.dataStore.edit { preferences ->
//            preferences[IS_PLAYING_KEY] = isPlaying
//        }
//    }
//
//    init {
////        // پخش موزیک از اول هنگام شروع اپلیکیشن
////        playMusic()
//        // وصل کردن ViewModel به کل پروسه اپلیکیشن
//        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
//
//        // هنگام ساخت ویو مدل مقدار ذخیره شده رو بخونیم
//        viewModelScope.launch {
//            val preferences = context.dataStore.data.first()
//            val isPlaying = preferences[IS_PLAYING_KEY] ?: false  // وضعیت موزیک رو می‌خونیم
//
//            _isPlaying.value = isPlaying
//            if (isPlaying) {
//                playMusic()  // اگر موزیک پخش بوده، پخش می‌کنیم
//            }
//        }
//    }
//
//    fun playMusic() {
//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(context, R.raw.music).apply {
//                isLooping = true
//            }
//        }
//        mediaPlayer?.seekTo(lastPosition)  // ادامه از موقعیت قبلی
//        mediaPlayer?.start()
//        _isPlaying.value = true
//    }
//
//     fun stopMusic() {
//        mediaPlayer?.let {
//            if (it.isPlaying) {
//                it.pause()
//                lastPosition =
//                    it.currentPosition ?: 0   // برای اینکه از اول شروع بشه، به صفر برمی‌گردونیم
//            }
//        }
//        _isPlaying.value = false  // مطمئن بشو که وضعیت ذخیره می‌شه
//         viewModelScope.launch {
//             saveMusicState(false)  // وضعیت "قطع" موزیک رو ذخیره می‌کنیم
//         }
//    }
//
//    fun setAdPlaying(isPlaying: Boolean) {
//        isAdPlaying = isPlaying
//        if (isAdPlaying) {
//            shouldResumeMusic =
//                _isPlaying.value  // ذخیره می‌کنیم که آیا موزیک در حال پخش بوده یا نه
//            stopMusic() // وقتی تبلیغ شروع شد، موزیک متوقف بشه
//        } else {
//            if (shouldResumeMusic) {
//                playMusic() // وقتی تبلیغ تموم شد، موزیک از همون لحظه‌ای که قطع شده ادامه پیدا کنه
//            }
//        }
//    }
//
//    fun handleAppBackground(isInBackground: Boolean) {
//        if (isInBackground) {
//            shouldResumeMusic = _isPlaying.value  // ذخیره کنیم که موزیک در حال پخش بوده یا نه
//            mediaPlayer?.pause()
//            _isPlaying.value = false
//        } else {
//            if (shouldResumeMusic) {
//                mediaPlayer?.start()
//                _isPlaying.value = true
//            }
//        }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        mediaPlayer?.release()
//        mediaPlayer = null
//    }
//}
//@HiltViewModel
//class MusicPlayerViewModel @Inject constructor(
//    @ApplicationContext private val context: Context
//) : ViewModel(), DefaultLifecycleObserver {
//
//    private var mediaPlayer: MediaPlayer? = null
//    private val _isPlaying = MutableStateFlow(false)
//    val isPlaying = _isPlaying.asStateFlow()
//
//    private var lastPosition = 0
//    private var shouldResumeMusic = false
//    private var isAdPlaying = false
//
//    private val IS_PLAYING_KEY = booleanPreferencesKey("is_playing_key")
//    private val LAST_POSITION_KEY = intPreferencesKey("last_position_key")
//
//    private suspend fun saveMusicState(isPlaying: Boolean, position: Int) {
//        context.dataStore.edit { preferences ->
//            preferences[IS_PLAYING_KEY] = isPlaying
//            preferences[LAST_POSITION_KEY] = position
//        }
//    }
//
//    init {
//        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
//
//        // مقداردهی اولیه از DataStore برای ادامه‌ی پخش
//        viewModelScope.launch {
//            val preferences = context.dataStore.data.first()
//            val isPlayingStored = preferences[IS_PLAYING_KEY] ?: false
//            lastPosition = preferences[LAST_POSITION_KEY] ?: 0
//
//            _isPlaying.value = isPlayingStored
//            if (isPlayingStored) {
//                playMusic(lastPosition)
//            }
//        }
//    }
//
//    private fun createMediaPlayer() {
//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(context, R.raw.music).apply {
//                isLooping = true
//            }
//        }
//    }
//
//    fun playMusic(startPosition: Int = 0) {
//        createMediaPlayer()
//        mediaPlayer?.seekTo(startPosition)
//        mediaPlayer?.start()
//        _isPlaying.value = true
//        lastPosition = startPosition
//
//        viewModelScope.launch {
//            saveMusicState(true, startPosition)  // ذخیره‌ی وضعیت پخش
//        }
//    }
//
//    fun stopMusic() {
//        mediaPlayer?.let {
//            if (it.isPlaying) {
//                it.pause()
//                lastPosition = it.currentPosition
//            }
//        }
//        _isPlaying.value = false
//
//        viewModelScope.launch {
//            saveMusicState(false, lastPosition)
//        }
//    }
//
//    fun setAdPlaying(isPlaying: Boolean) {
//        isAdPlaying = isPlaying
//        if (isAdPlaying) {
//            shouldResumeMusic = _isPlaying.value
//            stopMusic()
//        } else {
//            if (shouldResumeMusic) {
//                playMusic(lastPosition)
//            }
//        }
//    }
//
//    fun handleAppBackground(isInBackground: Boolean) {
//        if (isInBackground) {
//            shouldResumeMusic = _isPlaying.value
//            mediaPlayer?.pause()
//            _isPlaying.value = false
//
//            viewModelScope.launch {
//                saveMusicState(false, mediaPlayer?.currentPosition ?: 0)
//            }
//        } else {
//            viewModelScope.launch {
//                val preferences = context.dataStore.data.first()
//                val isPlayingStored = preferences[IS_PLAYING_KEY] ?: false
//                val savedPosition = preferences[LAST_POSITION_KEY] ?: 0
//
//                lastPosition = savedPosition
//                if (isPlayingStored) {
//                    playMusic(savedPosition)
//                }
//            }
//        }
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        mediaPlayer?.release()
//        mediaPlayer = null
//    }
//}

@HiltViewModel
class MusicPlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel(), DefaultLifecycleObserver {

    private val player: ExoPlayer = ExoPlayer.Builder(context).build()
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private var lastPosition = 0L
    private var shouldResumeMusic = false
    private var isAdPlaying = false

    // DataStore برای ذخیره وضعیت پخش موزیک
    private val IS_PLAYING_KEY = booleanPreferencesKey("is_playing_key")
    private val LAST_POSITION_KEY = longPreferencesKey("last_position_key")

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        // تنظیم فایل موزیک
        player.setMediaItem(MediaItem.fromUri("android.resource://${context.packageName}/${R.raw.music}"))
        player.repeatMode = Player.REPEAT_MODE_ONE
        player.prepare()

        // بازیابی وضعیت موزیک از DataStore
        viewModelScope.launch {
            context.dataStore.data.firstOrNull()?.let { preferences ->
                val isPlayingStored = preferences[IS_PLAYING_KEY] ?: false
                lastPosition = preferences[LAST_POSITION_KEY] ?: 0L

                _isPlaying.value = isPlayingStored
                if (isPlayingStored) {
                    playMusic(lastPosition)  // ادامه از موقعیت قبلی
                }
            }
        }
    }

    fun playMusic(startPosition: Long = lastPosition) {
        player.seekTo(startPosition)
        player.play()
        _isPlaying.value = true
        lastPosition = startPosition

        // ذخیره وضعیت پخش موزیک
        viewModelScope.launch {
            saveMusicState(true, startPosition)
        }
    }

    fun stopMusic() {
        player.pause()
        lastPosition = player.currentPosition
        _isPlaying.value = false

        // ذخیره وضعیت توقف موزیک
        viewModelScope.launch {
            saveMusicState(false, lastPosition)
        }
    }

    fun setAdPlaying(isPlaying: Boolean) {
        isAdPlaying = isPlaying
        if (isAdPlaying) {
            shouldResumeMusic = _isPlaying.value
            stopMusic()
        } else {
            if (shouldResumeMusic) {
                playMusic(lastPosition)
            }
        }
    }

    private suspend fun saveMusicState(isPlaying: Boolean, position: Long) {
        context.dataStore.edit { preferences ->
            preferences[IS_PLAYING_KEY] = isPlaying
            preferences[LAST_POSITION_KEY] = position
        }
    }

    override fun onCleared() {
        viewModelScope.launch {
            saveMusicState(_isPlaying.value, player.currentPosition)
        }
        player.release()
        super.onCleared()
    }

    //این قسمت جدید اضافه شد: مدیریت بک‌گراند و فورگراند شدن اپلیکیشن
    fun handleAppBackground(isInBackground: Boolean) {
        if (isInBackground) {
            shouldResumeMusic = _isPlaying.value  // ذخیره کنیم که موزیک در حال پخش بوده یا نه
            player.pause()
            _isPlaying.value = false
        } else {
            if (shouldResumeMusic) {
                player.play()
                _isPlaying.value = true
            }
        }
    }
}