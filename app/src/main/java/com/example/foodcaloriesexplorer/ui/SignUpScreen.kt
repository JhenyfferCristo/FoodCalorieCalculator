package com.example.foodcaloriesexplorer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodcaloriesexplorer.data.FakeData
import com.example.foodcaloriesexplorer.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(onSignupSuccess: () -> Unit, onSignInRequested: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
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
            val newUser = User(email, password, name, height, weight)
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

@Preview(showBackground = true)
@Composable
fun PreviewSignIn() {
    SignUpScreen(onSignupSuccess = {}, onSignInRequested = {})
}

