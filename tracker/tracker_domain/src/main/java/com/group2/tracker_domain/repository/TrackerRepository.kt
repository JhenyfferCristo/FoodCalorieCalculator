package com.group2.tracker_domain.repository

import com.group2.tracker_domain.models.TrackableFood
import com.group2.tracker_domain.models.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>>

}