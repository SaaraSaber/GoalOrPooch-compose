package ir.developer.goalorpooch_compose.model


data class TeamModel(
    val id: Int,
    var score: Int = 0,
    var hasGoal: Boolean = false,
    var numberOfEmptyGames: Int = 3,
    var numberCubes: Int = 2,
    var cards: List<CardModel> = emptyList(),
    var selectedCube: Boolean = false,
    var selectedCard: Boolean = false,
    var gotGoalDuel: Int = 0,
    var notGotGoalDuel: Int = 0,
)

