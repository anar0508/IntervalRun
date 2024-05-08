package com.application.intervalrun.model

import com.application.intervalrun.enums.CycleState
import com.application.intervalrun.enums.IntervalTypes

data class Interval(
    val intervalId: Int = 0,
    var intervalName: String = "",
    var intervalDuration: Int = 0,
    var intervalType: IntervalTypes = IntervalTypes.REST
) {
    private var remainingTime = intervalDuration
    private var remindBeforeEnding = false
    private var reminderTime: Int = 0
    private var intervalState: CycleState = CycleState.STOPPED
    fun startTimer(): Int {
        intervalState = CycleState.RUNNING
        return remainingTime
    }

    fun pauseTimer(pausedSecond: Int) {
        intervalState = CycleState.PAUSED
        remainingTime = pausedSecond
    }

    fun stopTimer() {
        intervalState = CycleState.STOPPED
        remainingTime = intervalDuration
    }
}