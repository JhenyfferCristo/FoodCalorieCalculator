package com.example.foodcaloriesexplorer.ui

import androidx.annotation.StringRes
import com.example.foodcaloriesexplorer.R

enum class AppScreen(@StringRes val title: Int) {
    DashboardScreen(title = R.string.app_name),
    CategoriesScreen(title = R.string.categories_screen_title),
    IngredientsScreen(title = R.string.ingredients_screen_title),
    SignIn(title= R.string.signIn_screen_title),
    SignUp(title= R.string.signUp_screen_title),
    MainContent(title= R.string.mainContent_screen_title),
    Profile(title = R.string.profile_screen_title)
}