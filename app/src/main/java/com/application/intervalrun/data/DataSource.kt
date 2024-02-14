package com.application.intervalrun.data

import com.application.intervalrun.model.TemplateCycle
import com.application.intervalrun.model.Timer
import com.application.intervalrun.model.UserCycle

class DataSource {
    fun loadUserCycles(): MutableList<UserCycle> {
        return mutableListOf(
            UserCycle(
                "My First Run",
                12314,
                mutableListOf(
                    Timer(timerName = "Run", 60, false),
                    Timer(timerName = "Rest", 60, true)
                )
            ),
            UserCycle("My Second Run", 123141, mutableListOf(Timer(timerName = "Jump", 60, false))),
            UserCycle("My Third Run", 12412412415, mutableListOf(Timer(timerName = "Walk", 60, false))),
            UserCycle(
                "My Fourth Run", 1241515,
                mutableListOf(Timer(timerName = "Squish that cat", 60, false))
            ),
            UserCycle("My Fifth Run", 451431, mutableListOf(Timer(timerName = "Fall", 60, false)))
        )
    }

    fun loadTemplateCycles(): Array<TemplateCycle> {
        return arrayOf(
            TemplateCycle(
                "5K Run",
                541321,
                arrayOf(
                    Timer(timerName = "Run", 60, false),
                    Timer(timerName = "Rest", 60, true)
                )
            ),
            TemplateCycle("10K Run", 634432, arrayOf(Timer(timerName = "Jump", 60, false))),
            TemplateCycle("15K Run", 624527, arrayOf(Timer(timerName = "Walk", 60, false))),
            TemplateCycle(
                "Halfmarathon Run", 753484,
                arrayOf(Timer(timerName = "Squish that cat", 60, false))
            ),
            TemplateCycle("Marathon Run", 523462, arrayOf(Timer(timerName = "Fall", 60, false)))
        )
    }
}