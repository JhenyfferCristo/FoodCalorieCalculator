package com.group2.onboarding_presentation.model

data class User(
    val email: String,
    val password: String,
    val name: String,
    val height: String,
    val weight: String,
    val caloriesGoal: Int = 0,
)

object UserManager {
    var currentUser: User? = null
}