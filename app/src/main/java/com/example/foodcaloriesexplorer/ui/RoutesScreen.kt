@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.foodcaloriesexplorer.ui

import ProfileScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import model.Category
import model.CategoryDataSource

var currentSelectedCategory: Category? = null;

@Composable
fun CaloriesAppBar(
    currentScreen: AppScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
        }
    )
}
@Composable
fun CaloriesApp(
    navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.SignIn.name
    )

    Scaffold(
        topBar = {
            CaloriesAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.SignIn.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.DashScreen.name) {
                CategoriesScreen(
                    categories =  CategoryDataSource.categories,
                    modifier = Modifier
                        .fillMaxSize(),
                    onCategoryClick = {category ->
                        run {
                            currentSelectedCategory = category
                            navController.navigate(AppScreen.IngredientsScreen.name)
                        }
                    }
                )
            }
            composable(route = AppScreen.CategoriesScreen.name) {
                CategoriesScreen(
                    categories =  CategoryDataSource.categories,
                    modifier = Modifier
                        .fillMaxSize(),
                    onCategoryClick = {category ->
                        run {
                            currentSelectedCategory = category
                            navController.navigate(AppScreen.IngredientsScreen.name)
                        }
                    }
                )
            }
            composable(route = AppScreen.IngredientsScreen.name) {
                IngredientsScreen(currentSelectedCategory)
            }
            composable(route = AppScreen.SignIn.name) {
                SignInScreen(
                    onSignInSuccess = { navController.navigate(AppScreen.MainContent.name) },
                    onSignUpRequested = { navController.navigate(AppScreen.SignUp.name) }
                )
            }
            composable(route = AppScreen.SignUp.name) {
                SignUpScreen(
                    onSignupSuccess = {  navController.navigate(AppScreen.SignIn.name) },
                    onSignInRequested = {  navController.navigate(AppScreen.SignIn.name) }
                )
            }
            composable(route = AppScreen.MainContent.name) {
                MainContent ( isSignIn = {navController.navigate(AppScreen.Profile.name)})
            }
            composable(route = AppScreen.MainContent.name) {
                ProfileScreen()
            }
        }
    }
}
