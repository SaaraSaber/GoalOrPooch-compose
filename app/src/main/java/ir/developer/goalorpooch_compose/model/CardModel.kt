package ir.developer.goalorpooch_compose.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.developer.goalorpooch_compose.R

@Entity(tableName = "card")
data class CardModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int = R.drawable.pic_card,
    var disable: Boolean = false
)