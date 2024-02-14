package com.application.intervalrun.ui.yourCyclesUI

import com.application.intervalrun.model.UserCycle

data class YourCyclesUiState(
    var userCycles: MutableList<UserCycle> = mutableListOf(),
    var unfoldedCycle: Long? = null
)
