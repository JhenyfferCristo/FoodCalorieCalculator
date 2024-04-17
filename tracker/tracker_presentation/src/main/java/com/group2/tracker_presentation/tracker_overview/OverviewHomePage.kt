package com.group2.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.group2.core_ui.LocalSpacing
import com.group2.tracker_presentation.R
import com.group2.tracker_presentation.tracker_overview.components.AddButton
import com.group2.tracker_presentation.tracker_overview.components.DaySelector
import com.group2.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.group2.tracker_presentation.tracker_overview.components.NutrientsHeader
import com.group2.tracker_presentation.tracker_overview.components.TrackedFoodItem

@Composable
fun OverviewHomePage(
    viewModel: TrackerOverviewViewModel,
    onNavigateToSearch: (String, Int, Int, Int) -> Unit
) {
    val state = viewModel.state
    val dimens = LocalSpacing.current
    val context = LocalContext.current

            LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                //.padding(bottom = innerPadding.calculateBottomPadding()),
                .padding(bottom = 8.dp),
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
