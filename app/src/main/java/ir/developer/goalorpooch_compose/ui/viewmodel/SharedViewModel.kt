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
import ir.developer.goalorpooch_compose.model.PlayerModel
import ir.developer.goalorpooch_compose.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: CardRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _allTasks =
        MutableStateFlow<List<CardModel>>(emptyList())
    val allCards: StateFlow<List<CardModel>> = _allTasks

    fun getAllCards() {
        viewModelScope.launch {
            repository.getCards.collect {
                _allTasks.value = it
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
            repository.addCard(cardModel = cardModel)
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
            repository.updateCard(cardModel)
        }
    }

    fun randomCardsTeam1(): List<CardModel> {
        val randomCards = allCards.value.shuffled().take(Utils.THE_NUMBER_OF_PLAYING_CARDS)
        PlayerModel(
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
        PlayerModel(
            id = 1,
            hasCard = true,
            numberKhaliBazy = 3,
            numberMokaab = 2,
            hasGoal = false,
            cards = randomCards
        )
        return randomCards
    }

    val hasCardTeam1 = false
    val hasCardTeam2 = false

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