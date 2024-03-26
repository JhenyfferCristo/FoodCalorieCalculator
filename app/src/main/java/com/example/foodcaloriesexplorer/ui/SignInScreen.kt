package com.example.foodcaloriesexplorer.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodcaloriesexplorer.data.FakeData
import com.example.foodcaloriesexplorer.data.UserManager

@Composable
fun SignInScreen(onSignInSuccess: () -> Unit, onSignUpRequested : () -> Unit) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var showDialog by remember { mutableStateOf(false) }
        var dialogMessage by remember { mutableStateOf("") }

        val context = LocalContext.current

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Login")

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

            Button(onClick = {
                val user = FakeData.authenticate(email, password)
                if (user != null) {
                    UserManager.currentUser = user
                    onSignInSuccess()
                } else {
                    dialogMessage = "Authentication failed."
                    showDialog = true
                }
            }, modifier = Modifier.align(Alignment.End)) {
                Text("Sign In")
            }

            if (showDialog){
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Alert") },
                    text = { Text(dialogMessage) },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    })
            }
            Button(onClick = {
                onSignUpRequested()
            }, modifier = Modifier.align(Alignment.End)) {
                Text("Sign Up")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewLogin() {
        SignInScreen(onSignInSuccess = { /* Navigate to your main activity */ }, onSignUpRequested = {})
    }

