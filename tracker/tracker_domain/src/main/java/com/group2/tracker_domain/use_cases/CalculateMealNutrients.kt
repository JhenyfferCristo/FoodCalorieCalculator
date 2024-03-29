package com.group2.tracker_domain.use_cases

import com.group2.core.domain.models.ActivityLevel
import com.group2.core.domain.models.GoalType
import com.group2.core.domain.preferences.Preferences
import com.group2.onboarding_presentation.model.User
import com.group2.onboarding_presentation.model.UserManager
import com.group2.tracker_domain.models.MealType
import com.group2.tracker_domain.models.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val fat: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )

    operator fun invoke(trackedFoods: List<TrackedFood>): Result {
        val allNutrients = trackedFoods
            .groupBy { _trackedFoods ->
                _trackedFoods.mealType
            }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = type
                )
            }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProteins = allNutrients.values.sumOf { it.protein }
        val totalFats = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val currentUser = UserManager.currentUser
        if (currentUser == null) {
            // Handle the case where no user is logged in
            throw IllegalStateException("No user is currently logged in.")
        }

        // Convert User to UserInfo if necessary, or directly use User if it fits your needs
        val userInfo = currentUser
        val caloryGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloryGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProteins,
            totalFat = totalFats,
            totalCalories = totalCalories,
            mealNutrients = allNutrients

        )
    }
    private fun bmr(user: User): Int {
        // Assuming age calculation or a static age value for demonstration purposes
        // In real scenarios, you'd calculate the age based on a birthdate or store it in the user model
        val age = 25 // Example static age, replace with actual age calculation if possible

        return when (user.gender) {
            "Male" -> {
                (66.47f + (13.75f * user.weight) +
                        (5.003f * user.height) - (6.75f * age)).roundToInt()
            }
            "Female" -> {
                (655.1f + (9.563f * user.weight) +
                        (1.850f * user.height) - (4.676f * age)).roundToInt()
            }
            else -> throw IllegalArgumentException("Invalid gender value")
        }
    }


    private fun dailyCaloryRequirement(userInfo: User): Int {
        val activityFactor = when (userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when (userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }
}