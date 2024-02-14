package com.application.intervalrun.enums

import androidx.annotation.StringRes
import com.application.intervalrun.R

enum class AppScreens(@StringRes val title: Int) {
    UserCycles(title = R.string.your_training_cycles),
    Templates(title = R.string.template_trainings),
    Wearable(title = R.string.wearables),
    Logs(title = R.string.logs),
    Settings(title = R.string.settings),
    NewCycle(title = R.string.new_cycle),
    ActiveTimer(title = R.string.active_timer)
}