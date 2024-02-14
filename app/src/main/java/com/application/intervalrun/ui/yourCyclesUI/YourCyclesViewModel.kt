package com.application.intervalrun.ui.yourCyclesUI

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class YourCyclesViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(YourCyclesUiState())
    val uiState: StateFlow<YourCyclesUiState> = _uiState.asStateFlow()
}