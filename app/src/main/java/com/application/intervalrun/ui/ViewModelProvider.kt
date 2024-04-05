package com.application.intervalrun.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.application.intervalrun.IntervalAppContainer
import com.application.intervalrun.ui.screens.editCycleScreenUI.EditCycleViewModel
import com.application.intervalrun.ui.screens.userCyclesScreenUI.UserCyclesViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            UserCyclesViewModel(
                intervalApplicationContainer().container.trainingCycleRepository
            )
        }
        initializer {
            EditCycleViewModel(
                this.createSavedStateHandle(),
                intervalApplicationContainer().container.trainingCycleRepository
            )
        }
    }
}


fun CreationExtras.intervalApplicationContainer(): IntervalAppContainer =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as IntervalAppContainer)
