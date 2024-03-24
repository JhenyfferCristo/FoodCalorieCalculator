package model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Ingredient (
    @StringRes val nameRes: Int,
    val category: Category,
    val fat: Double,
    val protein: Double,
    val carbs: Double,
    val calories: Int
)