package com.application.intervalrun.ui.screens.editCycleScreenUI

import com.application.intervalrun.data.entities.TrainingCycle
import com.application.intervalrun.enums.CycleState
import com.application.intervalrun.model.Timer


data class EditTrainingCycleUiState(
    val trainingCycleDetails: UserTrainingCycleDetails = UserTrainingCycleDetails(),
    val isEntryValid: Boolean = true,
)
data class UserTrainingCycleDetails(
    val name: String = "",
    val cycleState: CycleState = CycleState.STOPPED,
    val isTemplate: Boolean = false,
    val timers: MutableList<Timer> = mutableListOf(),
    val id: Int = 0
)

fun UserTrainingCycleDetails.toUserTrainingCycle(): TrainingCycle = TrainingCycle(
    cycleName = name,
    timers = timers,
    cycleState = cycleState,
    uid = id,
    isTemplate = false
)

fun TrainingCycle.toUserTrainingCycleDetails(): UserTrainingCycleDetails = UserTrainingCycleDetails(
    name = cycleName,
    timers = timers?: mutableListOf(),
    cycleState = cycleState,
    id = uid
)


fun TrainingCycle.toEditTrainingCycleUiState(isEntryValid: Boolean = false): EditTrainingCycleUiState = EditTrainingCycleUiState(
    trainingCycleDetails = this.toUserTrainingCycleDetails(),
    isEntryValid = isEntryValid
)
