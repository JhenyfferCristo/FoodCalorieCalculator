package com.group2.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.group2.onboarding_presentation.model.UserManager
import kotlin.math.roundToInt

@Composable
fun ProfilePage() {
    val user = UserManager.currentUser
    user?.let {
        val bmi = calculateBMI(it.weight, it.height).roundToInt()  // Round BMI for display
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Profile", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(20.dp))
            ProfileItem(label = "Email", value = it.email)
            ProfileItem(label = "Name", value = it.name)
            ProfileItem(label = "Gender", value = it.gender)
            ProfileItem(label = "Age", value = "${it.age}")
            ProfileItem(label = "Height", value = "${it.height} cm")
            ProfileItem(label = "Weight", value = "${it.weight} kg")
            ProfileItem(label = "BMI", value = "$bmi kg/m²")  // Display BMI
            ProfileItem(label = "Activity Level", value = it.activityLevel.name)
            ProfileItem(label = "Goal Type", value = it.goalType.name)

        }
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", style = MaterialTheme.typography.subtitle1)
        Text(text = value, style = MaterialTheme.typography.body1)
    }
}

fun calculateBMI(weight: Float, height: Float): Float {
    if (height == 0f) return 0f
    val heightInMeters = height / 100  // Convert height from cm to meters
    return weight / (heightInMeters * heightInMeters)
}
