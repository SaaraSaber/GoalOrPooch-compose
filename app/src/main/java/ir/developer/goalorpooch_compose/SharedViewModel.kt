package ir.developer.goalorpooch_compose

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.developer.goalorpooch_compose.database.repository.CardRepository
import ir.developer.goalorpooch_compose.model.CardModel
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
                Log.i("SelectCardScreen", "SelectCardScreen2: ${allCards.value}")
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

    private fun updateCard(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val cardModel = CardModel(
                id = id,
                name = "",
                description = "",
                isSelect = false,
                disable = false
            )
            repository.updateCard(cardModel)
        }
    }

}