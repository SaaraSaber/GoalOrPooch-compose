package ir.developer.goalorpooch_compose.util

import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.model.TeamModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TeamManager {
    private val _player = MutableStateFlow(
        listOf(
            TeamModel(id = 0),
            TeamModel(id = 1)
        )
    )

    val players: StateFlow<List<TeamModel>> = _player

    // اختصاص کارت‌های تصادفی به تیم خاص
    fun assignRandomCardsToPlayer(teamId: Int, allCards: List<CardModel>): List<CardModel> {
        val randomCards = allCards.shuffled().take(Utils.THE_NUMBER_OF_PLAYING_CARDS)
        _player.value = _player.value.map { player ->
            if (player.id == teamId) {
                player.copy(cards = randomCards, hasCard = true)
            } else {
                player
            }
        }
        return randomCards
    }

    // به‌روزرسانی ویژگی خاصی از PlayerModel
    fun updatePlayer(teamId: Int, update: TeamModel.() -> TeamModel) {
        _player.value = _player.value.map { player ->
            if (player.id == teamId) {
                player.update() // اعمال تغییرات
            } else {
                player
            }
        }
    }

    // گرفتن اطلاعات یک تیم خاص
    fun getPlayer(teamId: Int): TeamModel? {
        return _player.value.firstOrNull { it.id == teamId }
    }

    fun disableCardForPlayer(teamId: Int, cardId: Int) {
        _player.value = _player.value.map { playerModel ->
            if (playerModel.id == teamId) {
                val updateCards = playerModel.cards.map { cardModel ->
                    if (cardModel.id == cardId) cardModel.copy(disable = true) else cardModel
                }
                playerModel.copy(cards = updateCards)
            } else {
                playerModel
            }
        }
    }
}