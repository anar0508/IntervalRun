package com.application.intervalrun.model

import com.application.intervalrun.enums.CycleState


open class Cycle(open val cycleName: String) {

}
class UserCycle (override var cycleName: String, private var timers: MutableList<Timer>): Cycle(cycleName) {
    private var cycleState: CycleState = CycleState.STOPPED;

    fun startCycle() {
        cycleState = CycleState.RUNNING
    }

    fun pauseCycle() {
        cycleState = CycleState.PAUSED
    }

    fun stopCycle() {
        cycleState = CycleState.STOPPED
    }

    fun convertToTemplateCycle(templateCycleName: String): TemplateCycle {
        return TemplateCycle(cycleName= templateCycleName, timers = timers.toTypedArray())
    }

}

data class TemplateCycle (override val cycleName: String, val timers: Array<Timer>) : Cycle(cycleName) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TemplateCycle

        if (cycleName != other.cycleName) return false
        return timers.contentEquals(other.timers)
    }

    override fun hashCode(): Int {
        var result = cycleName.hashCode()
        result = 31 * result + timers.contentHashCode()
        return result
    }

}



