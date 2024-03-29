package com.group2.onboarding_presentation.model

object FakeData {
    private val users = mutableListOf<User>(
        User(
            email = "test@test.com",
            password = "1234",
            name = "TestUser",
            gender = "Female",
            age = 29,
            height = 170.0f,
            weight = 66.0f,
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