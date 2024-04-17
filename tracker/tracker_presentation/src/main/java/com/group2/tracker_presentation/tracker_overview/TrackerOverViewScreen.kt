package com.group2.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.group2.core.R
import com.group2.core_ui.LocalSpacing
import com.group2.tracker_presentation.tracker_overview.components.*
import androidx.compose.material.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.group2.tracker_presentation.tracker_overview.components.NutrientsHeader
import com.group2.tracker_presentation.tracker_overview.components.DaySelector
import com.group2.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.group2.tracker_presentation.tracker_overview.components.AddButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person

@ExperimentalCoilApi
@Composable
fun TrackerOverviewScreen(
    onNavigateToSearch: (String, Int, Int, Int) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val dimens = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding()),
            content = {
                item {
                    NutrientsHeader(state = state)
                    Spacer(modifier = Modifier.height(dimens.medium))
                    DaySelector(
                        date = state.date,
                        onPreviousDateClick = {
                            viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                        },
                        onNextDayClick = {
                            viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = dimens.medium)
                    )
                    Spacer(modifier = Modifier.height(dimens.medium))
                }
                items(state.meals) { meal ->
                    ExpandableMeal(
                        meal = meal,
                        content = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = dimens.small)
                            ) {
                                val foods = state.trackedFoods.filter {
                                    it.mealType == meal.mealType
                                }
                                foods.forEach { food ->
                                    TrackedFoodItem(
                                        trackedFood = food,
                                        onDeleteClick = {
                                            viewModel.onEvent(
                                                TrackerOverviewEvent.OnDeleteTrackedFoodClick(food)
                                            )
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(dimens.medium))
                                }
                                AddButton(
                                    text = stringResource(
                                        id = R.string.add_meal,
                                        meal.name.asString(context)
                                    ),
                                    onClick = {
                                        onNavigateToSearch(
                                            meal.name.asString(context),
                                            state.date.dayOfMonth,
                                            state.date.monthValue,
                                            state.date.year
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        },
                        onToggleClick = {
                            viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem("home", "Home", Icons.Default.Home),
        NavigationItem("recipes", "Recipes", Icons.Default.Menu),
        NavigationItem("profile", "Profile", Icons.Default.Person)
    )
    BottomNavigation {
        val currentRoute = navController.currentDestination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    when (item.route) {
                        "home" -> {
                            // Assuming "Home" is the route name for the home screen
                            navController.navigate("Home") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        "recipes" -> {
                            // Navigate to RecipePage
                            navController.navigate("RecipePage") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        "profile" -> {
                            // Navigate to ProfilePage
                            navController.navigate("ProfilePage") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        else -> {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            )
        }
    }
}

data class NavigationItem(val route: String, val label: String, val icon: ImageVector)
