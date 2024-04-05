package com.application.intervalrun.domain


import com.application.intervalrun.data.entities.TrainingCycle

class CycleManagerUseCase (userCycle: TrainingCycle) {
    var userCycle = userCycle;
    fun startCycle() {
//        cycleState = CycleState.RUNNING
    }

    fun pauseCycle() {
//        cycleState = CycleState.PAUSED
    }

    fun stopCycle() {
//        cycleState = CycleState.STOPPED
    }

//    fun convertToTemplateCycle(templateCycleName: String): TrainingCycle {
//        return TrainingCycle(cycleName= templateCycleName, cycleId = 12415515, timers = arrayOf<Timer>())
//    }

}