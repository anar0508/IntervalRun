package com.application.intervalrun.model

import com.application.intervalrun.enums.CycleState

class Timer (var timerName: String, var timerDuration: Int, var restingPeriod: Boolean ) {
    var remainingTime = timerDuration;
    private var remindBeforeEnding = false;
    private var reminderTime: Int = 0;
    private var timerState: CycleState = CycleState.STOPPED
    fun startTimer(): Int {
        timerState = CycleState.RUNNING
        return remainingTime;
    }

    fun pauseTimer(pausedSecond: Int) {
        timerState = CycleState.PAUSED
        remainingTime = pausedSecond;
    }

    fun stopTimer() {
        timerState = CycleState.STOPPED
        remainingTime = timerDuration;
    }
}