package com.group2.onboarding_presentation.model

object FakeData {
    private val users = mutableListOf<User>(
        User(
            email = "test@test.com",
            password = "1234",
            name = "TestUser",
            height = "170",
            weight = "65",
        )
    )
    fun addUser(user: User): Boolean {
        if (users.any { it.email == user.email }) {
            // User already exists
            return false
        }
        users.add(user)
        return true
    }

    fun authenticate(email: String, password: String): User? {
        return users.firstOrNull { it.email == email && it.password == password }
    }
}