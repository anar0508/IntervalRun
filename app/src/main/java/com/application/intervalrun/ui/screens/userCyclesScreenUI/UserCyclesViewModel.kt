package com.application.intervalrun.ui.screens.userCyclesScreenUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.intervalrun.data.repository.TrainingCycleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserCyclesViewModel ( trainingCycleRepository: TrainingCycleRepository): ViewModel() {
    val userTrainingCyclesUiState: StateFlow<UserTrainingCyclesUiState> = trainingCycleRepository.userTrainingCycles.map {
        UserTrainingCyclesUiState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = UserTrainingCyclesUiState()
    )
}