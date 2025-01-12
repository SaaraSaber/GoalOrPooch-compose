package ir.developer.goalorpooch_compose.model

import androidx.annotation.DrawableRes

data class ResultThisRoundModel(
    val id:Int,
    @DrawableRes
    val image:Int,
    val text:String
)
