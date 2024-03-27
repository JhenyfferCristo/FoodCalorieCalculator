package com.group2.tracker_domain.use_cases

data class TrackerUseCases(
    val calculateMealNutrients: CalculateMealNutrients,
    val deleteTrackedFood: DeleteTrackedFood,
    val getFoodsForDate: GetFoodsForDate,
    val searchFood: SearchFood,
    val trackFood: TrackFood
)
