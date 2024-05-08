package com.application.intervalrun.ui.screens.editCycleScreenUI

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.intervalrun.data.repository.TrainingCycleRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
private const val TAG = "EditCycleViewModel"
class EditCycleViewModel(
    savedStateHandle: SavedStateHandle,
    private val trainingCycleRepository: TrainingCycleRepository
): ViewModel() {
    var editTrainingCycleUiState by mutableStateOf(EditTrainingCycleUiState())
        private set

    init {
        viewModelScope.launch {
            editTrainingCycleUiState = trainingCycleRepository.getTrainingCycle(0)
                .filterNotNull()
                .first()
                .toEditTrainingCycleUiState(true)
        }
    }

    fun updateUiState(trainingCycleDetails: UserTrainingCycleDetails) {
        editTrainingCycleUiState =
            EditTrainingCycleUiState(trainingCycleDetails = trainingCycleDetails, isEntryValid = validateInput(trainingCycleDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            trainingCycleRepository.insert(editTrainingCycleUiState.trainingCycleDetails.toUserTrainingCycle())
        }
    }
    private fun validateInput(uiState: UserTrainingCycleDetails = editTrainingCycleUiState.trainingCycleDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && intervals.size != 0
        }
    }

}
