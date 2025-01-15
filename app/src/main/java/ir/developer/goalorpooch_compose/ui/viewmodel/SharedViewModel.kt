package ir.developer.goalorpooch_compose.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.database.repository.CardRepository
import ir.developer.goalorpooch_compose.database.repository.SettingRepository
import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.model.GameGuideModel
import ir.developer.goalorpooch_compose.model.PlayerModel
import ir.developer.goalorpooch_compose.model.SettingModel
import ir.developer.goalorpooch_compose.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val settingRepository: SettingRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    //..........................cards.............
    private val _allCards =
        MutableStateFlow<List<CardModel>>(emptyList())
    val allCards: StateFlow<List<CardModel>> = _allCards

    fun getAllCards() {
        viewModelScope.launch {
            cardRepository.getCards.collect {
                _allCards.value = it
            }
        }
    }

    fun addCard(id: Int, name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModel = CardModel(
                id = id,
                name = name,
                description = description,
                isSelect = false,
                disable = false
            )
            cardRepository.addCard(cardModel = cardModel)
        }
    }

    fun updateCard(
        id: Int,
        name: String,
        description: String,
        isSelect: Boolean,
        disable: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModel = CardModel(
                id = id,
                name = name,
                description = description,
                isSelect = isSelect,
                disable = disable
            )
            cardRepository.updateCard(cardModel)
        }
    }


    //...................setting...................

//    private val _allItemSetting =
//        MutableStateFlow<SettingModel>(
//            value =
//            SettingModel(
//                id = 0,
//                playerNumber = 6,
//                victoryPoint = 9,
//                getTimeToGetGoal = 90,
//                getTimeToGetShahGoal = 180,
//                countOfPlayingCards = 5
//            )
//        )

    var itemSetting = mutableStateOf(
        SettingModel(
            id = 0,
            playerNumber = 6,
            victoryPoint = 9,
            getTimeToGetGoal = 90,
            getTimeToGetShahGoal = 180,
            countOfPlayingCards = 5
        )
    )
        private set

    fun updateItemSetting(newSetting: SettingModel) {
        itemSetting.value = newSetting
    }

//    fun addItemSetting(
//        id: Int,
//        playerNumber: Int,
//        victoryPoint: Int,
//        getTimeToGetGoal: Int,
//        getTimeToGetShahGoal: Int,
//        countOfPlayingCards: Int
//    ) {
//        viewModelScope.launch {
//            val settingModel = SettingModel(
//                id = id,
//                playerNumber = playerNumber,
//                victoryPoint = victoryPoint,
//                getTimeToGetGoal = getTimeToGetGoal,
//                getTimeToGetShahGoal = getTimeToGetShahGoal,
//                countOfPlayingCards = countOfPlayingCards
//            )
//            settingRepository.addItemSetting(settingModel = settingModel)
//        }
//    }
//
//    fun getItemSetting() {
//        viewModelScope.launch {
//            settingRepository.getItemSetting.collect {
//                _allItemSetting.value = it
//            }
//        }
//    }

    //.........manegeGame............................

    fun randomCardsTeam1(): List<CardModel> {
        val randomCards = allCards.value.shuffled().take(Utils.THE_NUMBER_OF_PLAYING_CARDS)
        val teamOne = PlayerModel(
            id = 0,
            hasCard = true,
            numberKhaliBazy = 3,
            numberMokaab = 2,
            hasGoal = false,
            cards = randomCards
        )
        return randomCards
    }

    fun randomCardsTeam2(): List<CardModel> {
        val randomCards = allCards.value.shuffled().take(Utils.THE_NUMBER_OF_PLAYING_CARDS)
        val teamTwo = PlayerModel(
            id = 1,
            hasCard = true,
            numberKhaliBazy = 3,
            numberMokaab = 2,
            hasGoal = false,
            cards = randomCards
        )
        return randomCards
    }

//...................guide............................
    fun getItemsGameGuide(): List<GameGuideModel> {
        return listOf(
            GameGuideModel(
                title = context.getString(R.string.game_guide_title_how_to_play),
                description = context.getString(R.string.game_guide_description_how_to_play)
            ),
            GameGuideModel(
                title = context.getString(R.string.game_guide_title_cards),
                description = context.getString(R.string.game_guide_description_cards)
            ),
            GameGuideModel(
                title = context.getString(R.string.game_guide_title_card_order),
                description = context.getString(R.string.game_guide_description_card_order)
            ),
            GameGuideModel(
                title = context.getString(R.string.game_guide_title_king_goal),
                description = context.getString(R.string.game_guide_description_king_goal)
            ),
            GameGuideModel(
                title = context.getString(R.string.game_guide_title_scoring),
                description = context.getString(R.string.game_guide_description_scoring)
            ),
            GameGuideModel(
                title = context.getString(R.string.game_guide_title_cube),
                description = context.getString(R.string.game_guide_description_cube)
            ),
            GameGuideModel(
                title = context.getString(R.string.game_guide_title_important_notes),
                description = context.getString(R.string.game_guide_description_important_notes)
            )
        )
    }
}