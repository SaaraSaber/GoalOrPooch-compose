package ir.developer.goalorpooch_compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.developer.goalorpooch_compose.database.repository.CardRepository
import ir.developer.goalorpooch_compose.model.CardModelTeamOne
import ir.developer.goalorpooch_compose.model.CardModelTeamTwo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {

    private val _allTasksTeamOne =
        MutableStateFlow<List<CardModelTeamOne>>(emptyList())
    val allCardsTeamOne: StateFlow<List<CardModelTeamOne>> = _allTasksTeamOne

    fun getAllCardsTeamOne() {
        viewModelScope.launch {
            repository.getCardsTeamOne.collect {
                _allTasksTeamOne.value = it
            }
        }
    }

    private val _allTasksTeamTwo =
        MutableStateFlow<List<CardModelTeamTwo>>(emptyList())
    val allCardsTeamTwo: StateFlow<List<CardModelTeamTwo>> = _allTasksTeamTwo

    fun getAllCardsTeamTwo() {
        viewModelScope.launch {
            repository.getCardsTeamTwo.collect {
                _allTasksTeamTwo.value = it
            }
        }
    }

    private val _allCardSelectTeamOne =
        MutableStateFlow<List<CardModelTeamOne>>(emptyList())
    val allCardSelectTeamOne: StateFlow<List<CardModelTeamOne>> = _allCardSelectTeamOne

    fun getCardsSelectTeamOne(isSelect: Boolean) {
        viewModelScope.launch {
            repository.getCardsSelectTeamOne(isSelect).collect { card ->
                _allCardSelectTeamOne.value = card
            }
        }

    }

    fun addCardTeamOne(id: Int, name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModelTeamOne = CardModelTeamOne(
                id = id,
                name = name,
                description = description,
                isSelect = false,
                disable = false
            )
            repository.addCardTeamOne(cardModelTeamOne = cardModelTeamOne)
        }
    }

    fun addCardTeamTwo(id: Int, name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModelTeamTwo = CardModelTeamTwo(
                id = id,
                name = name,
                description = description,
                isSelect = false,
                disable = false
            )
            repository.addCardTeamTwo(cardModelTeamTwo = cardModelTeamTwo)
        }
    }

    fun updateCardTeamOne(
        id: Int,
        name: String,
        description: String,
        isSelect: Boolean,
        disable: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModelTeamOne = CardModelTeamOne(
                id = id,
                name = name,
                description = description,
                isSelect = isSelect,
                disable = disable
            )
            repository.updateCardTeamOne(cardModelTeamOne)
        }
    }

    fun updateCardTeamTwo(
        id: Int, name: String,
        description: String,
        isSelect: Boolean,
        disable: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModelTeamTwo = CardModelTeamTwo(
                id = id,
                name = name,
                description = description,
                isSelect = isSelect,
                disable = disable
            )
            repository.updateCardTeamTwo(cardModelTeamTwo)
        }
    }

}