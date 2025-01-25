package ir.developer.goalorpooch_compose.util

import android.util.Log
import ir.developer.goalorpooch_compose.model.CardModel
import ir.developer.goalorpooch_compose.model.TeamModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamManager @Inject constructor() {
    private val _team = MutableStateFlow(
        listOf(
            TeamModel(id = 0),
            TeamModel(id = 1)
        )
    )

    val teams: StateFlow<List<TeamModel>> = _team

    // اختصاص کارت‌های تصادفی به تیم خاص
    fun assignRandomCardsToPlayer(teamId: Int, allCards: List<CardModel>): List<CardModel> {
        val randomCards = allCards.shuffled().take(Utils.THE_NUMBER_OF_PLAYING_CARDS)
        _team.value = _team.value.map { player ->
            if (player.id == teamId) {
                player.copy(cards = randomCards)
            } else {
                player
            }
        }
        return randomCards
    }

    // به‌روزرسانی ویژگی خاصی از TeamModel
    fun updateTeam(teamId: Int, update: TeamModel.() -> TeamModel) {
        _team.value = _team.value.map { team ->
            if (team.id == teamId) {
                team.update() // اعمال تغییرات
            } else {
                team
            }
        }
    }

    // به‌روزرسانی امتیاز از TeamModel
    fun updateScoreTeam(teamId: Int, newScore: Int, maxScore: Int, update: TeamModel.() -> TeamModel) {
        _team.value = _team.value.map { team ->
            if (team.id == teamId) {
                val updatedScore = team.score + newScore
                Log.i("updateScoreTeam", "Updated Score: $updatedScore")

                if (maxScore == updatedScore || maxScore > updatedScore) {
                    team.update()
                } else if (maxScore < updatedScore) {
                    team.update(score = maxScore)
                } else {
                    team.update(score = updatedScore)
                }

//                team.update(team.score = when {
//                        updatedScore > maxScore -> maxScore // اگر از مقدار ماکس بیشتر باشد
//                        updatedScore == maxScore -> maxScore // اگر از مقدار ماکس بیشتر باشد
//                        else -> updatedScore // مقدار جدید معتبر است
//                    }
//                )
            } else {
                team
            }
        }
        _team.value.forEach {

            Log.i("updateScoreTeam", "updateScoreTeam: ${it.score} id: ${it.id}")
        }
    }

    // گرفتن اطلاعات یک تیم خاص
    fun getInfoTeam(teamId: Int): TeamModel? {
        return _team.value.firstOrNull { it.id == teamId }
    }

    fun disableCardForTeam(teamId: Int, cardId: Int) {
        _team.value = _team.value.map { teamModel ->
            if (teamModel.id == teamId) {
                val updateCards = teamModel.cards.map { cardModel ->
                    if (cardModel.id == cardId) cardModel.copy(disable = true) else cardModel
                }
                teamModel.copy(cards = updateCards)
            } else {
                teamModel
            }
        }
    }

    //اپدیت کلی تیم
    fun updateTeams(newTeams: List<TeamModel>) {
        _team.value = newTeams
    }


}