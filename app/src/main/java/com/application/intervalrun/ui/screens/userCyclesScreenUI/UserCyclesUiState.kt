package com.application.intervalrun.ui.screens.userCyclesScreenUI

import com.application.intervalrun.data.entities.TrainingCycle


data class UserTrainingCyclesUiState(
    var userCycles: List<TrainingCycle> = listOf(),
)
