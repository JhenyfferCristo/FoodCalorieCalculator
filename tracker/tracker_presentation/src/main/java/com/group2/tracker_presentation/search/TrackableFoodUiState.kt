package com.group2.tracker_presentation.search

import com.group2.tracker_domain.models.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
