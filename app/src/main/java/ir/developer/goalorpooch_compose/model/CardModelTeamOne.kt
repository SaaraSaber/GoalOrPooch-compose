package ir.developer.goalorpooch_compose.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.developer.goalorpooch_compose.R

@Entity(tableName = "cardTeamOne")
data class CardModelTeamOne(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes
    val imageTeamOne: Int = R.drawable.card_team_one,
    val isSelect: Boolean,
    var disable: Boolean
)