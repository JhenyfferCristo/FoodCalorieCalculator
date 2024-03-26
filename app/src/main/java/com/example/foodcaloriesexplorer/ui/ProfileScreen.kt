
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodcaloriesexplorer.data.UserManager

@Composable
fun ProfileScreen() {
    UserManager.currentUser?.let { user ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            ProfileItem(label = "Name", value = user.name)
            ProfileItem(label = "Email", value = user.email)
            ProfileItem(label = "Height", value = "${user.height} cm")
            ProfileItem(label = "Weight", value = "${user.weight} kg")
            val bmi = calculateBMI(user.height.toDouble(), user.weight.toDouble())
            Text(
                text = "BMI: ${String.format("%.2f", bmi)} (${bmiCategory(bmi)})",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    } ?: run {
        Text("No user information available.")
    }
}

@Composable
fun ProfileItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", style = MaterialTheme.typography.bodyLarge)
        Text(text = value, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp))
    }
}

fun calculateBMI(height: Double, weight: Double): Double {
    // Assume height is in centimeters. Convert to meters before calculation
    val heightInMeters = height / 100
    return weight / (heightInMeters * heightInMeters)
}

fun bmiCategory(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Underweight"
        bmi < 25 -> "Normal weight"
        bmi < 30 -> "Overweight"
        else -> "Obesity"
    }
}
