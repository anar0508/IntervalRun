package com.application.intervalrun.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.application.intervalrun.data.entities.TrainingCycle
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingCycleDao {
    @Query("SELECT * FROM training_cycle")
    fun getAllCycles(): List<TrainingCycle>

    @Query("SELECT * FROM training_cycle WHERE isTemplate = 0")
    fun getUserTrainingCycles(): Flow<List<TrainingCycle>>

    @Query("SELECT * FROM training_cycle WHERE isTemplate = 1")
    fun getTemplateTrainingCycles(): Flow<List<TrainingCycle>>

    @Query("SELECT * FROM training_cycle WHERE uid = :id")
    fun getTrainingCycle(id: Int): Flow<TrainingCycle?>

    @Insert
    fun insert(vararg newTrainingCycle: TrainingCycle)

    @Update
    fun update(trainingCycle: TrainingCycle)

    @Delete
    fun delete(trainingCycle: TrainingCycle)
}