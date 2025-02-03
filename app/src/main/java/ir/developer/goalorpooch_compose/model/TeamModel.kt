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
    var duelResults: List<DuelResult> = emptyList() // اضافه کردن نتایج دوئل
)

data class DuelResult(
    val playerId: Int,
    var result: ResultType = ResultType.PENDING
)

enum class ResultType {
    PENDING, // هنوز دوئل انجام نشده
    WIN,     // گل حفظ شده (چراغ سبز)
    LOSE     // گل از دست رفته (چراغ قرمز)
}
