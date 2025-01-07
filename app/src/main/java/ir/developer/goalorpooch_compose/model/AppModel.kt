package ir.developer.goalorpooch_compose.model

import androidx.annotation.DrawableRes

data class AppModel(
    val id: Int,
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int
)
