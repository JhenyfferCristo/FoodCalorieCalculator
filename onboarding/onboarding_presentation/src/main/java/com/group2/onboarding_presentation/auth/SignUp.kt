package com.group2.onboarding_presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.group2.onboarding_presentation.model.FakeData
import com.group2.onboarding_presentation.model.User


@Composable
fun SignUpScreen(onSignupSuccess: () -> Unit, onSignInRequested: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var age by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    // Add more fields as needed

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Signup")


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Gender")
        GenderRadioGroup(selectedGender = gender, onGenderSelected = { gender = it })

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height(cm)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight(kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            val newUser = User(email, password, name, gender, age.toInt(), height.toFloat(), weight.toFloat())
            val success = FakeData.addUser(newUser)
            if (success) {
                dialogMessage = "Signup Successful. Welcome!"
                showDialog = true
            } else {
                dialogMessage = "Existing User."
                showDialog = true
            }
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Sign Up")
        }
        if (showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Alert") },
                text = { Text(dialogMessage) },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        if (dialogMessage == "Signup Successful. Welcome!") {
                            onSignupSuccess() // Move the navigation or success action here
                        }
                    }) {
                        Text("OK")
                    }
                })
        }

        Button(onClick = {
            onSignInRequested()
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Go back to Login In page")
        }


    }
}

@Composable
fun GenderRadioGroup(selectedGender: String, onGenderSelected: (String) -> Unit) {
    val options = listOf("Male", "Female")

    Column {
        options.forEach { gender ->
            Row(modifier = Modifier.padding(8.dp)) {
                RadioButton(
                    selected = gender == selectedGender,
                    onClick = { onGenderSelected(gender) }
                )
                Text(
                    text = gender,
                    modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
                )
            }
        }
    }
}
