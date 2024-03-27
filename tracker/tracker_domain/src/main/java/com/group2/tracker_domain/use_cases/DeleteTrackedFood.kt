package com.group2.tracker_domain.use_cases

import com.group2.tracker_domain.models.TrackedFood
import com.group2.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        return repository.deleteTrackedFood(trackedFood)
    }
}