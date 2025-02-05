package ir.developer.goalorpooch_compose.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.developer.goalorpooch_compose.R
import ir.developer.goalorpooch_compose.database.repository.CardRepository
import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.model.GameGuideModel
import ir.developer.goalorpooch_compose.model.SettingModel
import ir.developer.goalorpooch_compose.model.TeamModel
import ir.developer.goalorpooch_compose.util.TeamManager
import ir.developer.goalorpooch_compose.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val cardRepository: CardRepository,
    private val teamManager: TeamManager, // حالا از طریق Hilt مقداردهی می‌شود
    @ApplicationContext private val context: Context
) : ViewModel() {
    init {
        // مقداردهی اولیه یا بارگذاری داده‌ها
        initializeTeams()
    }

    private val _player = MutableStateFlow(
        listOf(
            TeamModel(id = 0),
            TeamModel(id = 1)
        )
    )


    private fun initializeTeams() {
        // به‌عنوان مثال، تیم‌ها را از منابع خارجی بارگذاری کن.
        val initialTeams = listOf(
            TeamModel(id = 0, hasGoal = false),
            TeamModel(id = 1, hasGoal = false)
        )
        teamManager.updateTeams(initialTeams)
    }

    fun updateScoreTeam(teamId: Int, newScore: Int) {
        val maxScore = itemSetting.value.victoryPoint - 1
        teamManager.updateScoreTeam(teamId = teamId, newScore = newScore, maxScore = maxScore)
    }

    // تیم‌ها
    val teams: StateFlow<List<TeamModel>> = teamManager.teams

    // اختصاص کارت‌های تصادفی به یک تیم
    fun assignRandomCardsToTeam(teamId: Int, allCards: List<CardModel>): List<CardModel> {
        val listCards = teamManager.assignRandomCardsToPlayer(teamId, allCards)
        return listCards
    }

    // به‌روزرسانی ویژگی خاصی از یک تیم
    fun updateTeam(teamId: Int, update: TeamModel.() -> TeamModel) {
        teamManager.updateTeam(teamId, update)
    }

    // گرفتن اطلاعات یک تیم خاص
    fun getTeam(teamId: Int): TeamModel? {
        return teamManager.getInfoTeam(teamId)
    }

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
                disable = false
            )
            cardRepository.addCard(cardModel = cardModel)
        }
    }

    fun disableCardForPlayer(teamId: Int, cardId: Int) {
        teamManager.disableCardForTeam(teamId, cardId)
    }


    //...................setting...................

    private val _itemSetting = MutableStateFlow(
        SettingModel(
            id = 0,
            playerNumber = 6,
            victoryPoint = 9,
            getTimeToGetGoal = 90,
            getTimeToGetShahGoal = 180,
            countOfPlayingCards = Utils.THE_NUMBER_OF_PLAYING_CARDS,
            shahGoal = false,
            countShahGoal = 0,
            duel = false
        )
    )
    val itemSetting: StateFlow<SettingModel> = _itemSetting.asStateFlow()

    fun updateItemSetting(newSetting: SettingModel) {
        _itemSetting.update { newSetting }
        Utils.THE_NUMBER_OF_PLAYING_CARDS = newSetting.countOfPlayingCards
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