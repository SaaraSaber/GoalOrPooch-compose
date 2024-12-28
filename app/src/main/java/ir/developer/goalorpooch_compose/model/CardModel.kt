package ir.developer.goalorpooch_compose.model

import androidx.annotation.DrawableRes

data class CardModel(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val isSelect: Boolean,
    val disable: Boolean
)
