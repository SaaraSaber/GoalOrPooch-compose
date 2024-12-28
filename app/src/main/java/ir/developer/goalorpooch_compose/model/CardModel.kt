package ir.developer.goalorpooch_compose.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.developer.goalorpooch_compose.R

@Entity(tableName = "cards")
data class CardModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes
    val imageTeamOne: Int = R.drawable.card_team_one,
    @DrawableRes
    val imageTeamTwo: Int = R.drawable.card_team_two,
    val isSelect: Boolean,
    val disable: Boolean
)
