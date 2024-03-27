package com.group2.tracker_domain.di

import com.group2.core.domain.preferences.Preferences
import com.group2.tracker_domain.repository.TrackerRepository
import com.group2.tracker_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            calculateMealNutrients = CalculateMealNutrients(preferences),
            deleteTrackedFood = DeleteTrackedFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            searchFood = SearchFood(repository),
            trackFood = TrackFood(repository)
        )
    }

}