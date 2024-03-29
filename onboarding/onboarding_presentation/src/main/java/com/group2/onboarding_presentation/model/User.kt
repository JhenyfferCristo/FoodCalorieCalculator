package com.group2.onboarding_presentation.model

import com.group2.core.domain.models.ActivityLevel
import com.group2.core.domain.models.GoalType

data class User(
    val email: String,
    val password: String,
    val name: String,
    val gender: String,
    val age: Int,
    val height: Float,
    val weight: Float,
    val caloriesGoal: Int = 0,
    val activityLevel: ActivityLevel = ActivityLevel.Medium,
    val goalType: GoalType = GoalType.KeepWeight,
    val carbRatio: Float = 40.0f,
    val proteinRatio: Float = 40.0f,
    val fatRatio: Float = 40.0f
)

fun Float.getPercent(): Int {
    return (this * 100f).toInt()
}
object UserManager {
    var currentUser: User? = null
}