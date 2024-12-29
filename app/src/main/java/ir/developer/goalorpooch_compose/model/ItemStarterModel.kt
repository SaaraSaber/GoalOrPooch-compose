package ir.developer.goalorpooch_compose.model

import androidx.annotation.DrawableRes

data class ItemStarterModel(
    val id: Int,
    @DrawableRes
    val image: Int,
    val text: String
)
