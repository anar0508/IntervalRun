package com.application.intervalrun.model

import com.application.intervalrun.enums.CycleState


open class Cycle(open val cycleName: String, open val cycleId: Long) {

}
class UserCycle (override var cycleName: String, override val cycleId: Long, private var timers: MutableList<Timer>): Cycle(cycleName, cycleId) {
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
        return TemplateCycle(cycleName= templateCycleName, cycleId = 12415515, timers = timers.toTypedArray())
    }

}

data class TemplateCycle (override val cycleName: String, override val cycleId: Long, val timers: Array<Timer>) : Cycle(cycleName, cycleId) {
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



