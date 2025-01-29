package ir.developer.goalorpooch_compose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_setting")
data class SettingModel(
    @PrimaryKey
    val id: Int = 0,
    var playerNumber: Int = 6,
    var victoryPoint: Int = 9,
    var getTimeToGetGoal: Int = 90,
    var getTimeToGetShahGoal: Int = 180,
    var countOfPlayingCards: Int = 5,
    var shahGoal: Boolean = false,
    var countShahGoal: Int = 0
)