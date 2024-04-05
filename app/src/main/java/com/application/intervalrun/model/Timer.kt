package com.application.intervalrun.model

import com.application.intervalrun.enums.CycleState
import com.application.intervalrun.enums.TimerTypes

class Timer(
    val timerId: Int = 0,
    var timerName: String = "",
    private var timerDuration: Int = 0,
    private var timerType: TimerTypes = TimerTypes.REST
) {
    private var remainingTime = timerDuration
    private var remindBeforeEnding = false
    private var reminderTime: Int = 0
    private var timerState: CycleState = CycleState.STOPPED
    fun startTimer(): Int {
        timerState = CycleState.RUNNING
        return remainingTime
    }

    fun pauseTimer(pausedSecond: Int) {
        timerState = CycleState.PAUSED
        remainingTime = pausedSecond
    }

    fun stopTimer() {
        timerState = CycleState.STOPPED
        remainingTime = timerDuration
    }
}