package com.group2.tracker_domain.use_cases

import com.group2.tracker_domain.models.MealType
import com.group2.tracker_domain.models.TrackableFood
import com.group2.tracker_domain.models.TrackedFood
import com.group2.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        repository.insertTrackedFood(
            food = TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
                calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                date = date,
                amount = amount
            )
        )
    }
}