package ir.developer.goalorpooch_compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.developer.goalorpooch_compose.database.repository.CardRepository
import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.model.PlayerModel
import ir.developer.goalorpooch_compose.util.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: CardRepository
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

}