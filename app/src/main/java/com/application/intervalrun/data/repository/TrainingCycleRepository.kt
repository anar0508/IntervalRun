package com.application.intervalrun.data.repository

import androidx.annotation.WorkerThread
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.application.intervalrun.data.dao.TrainingCycleDao
import com.application.intervalrun.data.entities.TrainingCycle
import kotlinx.coroutines.flow.Flow

class TrainingCycleRepository(private val trainingCycleDao: TrainingCycleDao) {
    val userTrainingCycles: Flow<List<TrainingCycle>> = trainingCycleDao.getUserTrainingCycles()
    val templateTrainingCycles: Flow<List<TrainingCycle>> =
        trainingCycleDao.getTemplateTrainingCycles()

    suspend fun insert(trainingCycle: TrainingCycle) {
        trainingCycleDao.insert(trainingCycle)
    }

    suspend fun getTrainingCycle(id: Int): Flow<TrainingCycle?> {
        return trainingCycleDao.getTrainingCycle(id)
    }

}