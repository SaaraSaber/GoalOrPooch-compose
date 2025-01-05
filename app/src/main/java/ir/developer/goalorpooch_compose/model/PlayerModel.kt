package ir.developer.goalorpooch_compose.model

data class PlayerModel(
    val id: Int,
    val hasCard: Boolean,
    val numberKhaliBazy: Int,
    val numberMokaab: Int,
    val hasGoal: Boolean,
    val cards: List<CardModel>
)
