package com.example.foodcaloriesexplorer

import ProfileScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.foodcaloriesexplorer.ui.AppScreen
import com.example.foodcaloriesexplorer.ui.MainContent
import com.example.foodcaloriesexplorer.ui.SignInScreen
import com.example.foodcaloriesexplorer.ui.SignUpScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentScreen = remember { mutableStateOf(AppScreen.SignIn) }

            when (currentScreen.value) {
                AppScreen.SignIn -> SignInScreen(
                    onSignInSuccess = { currentScreen.value = AppScreen.MainContent },
                    onSignUpRequested = { currentScreen.value = AppScreen.SignUp }
                )
                AppScreen.SignUp -> SignUpScreen(
                    onSignupSuccess = { currentScreen.value = AppScreen.SignIn },
                    onSignInRequested = { currentScreen.value = AppScreen.SignIn }
                )
                AppScreen.MainContent -> MainContent ( isSignIn = {currentScreen.value = AppScreen.Profile})
                AppScreen.Profile -> ProfileScreen()
            }
        }
    }
}