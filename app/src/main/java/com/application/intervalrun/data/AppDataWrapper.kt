package com.application.intervalrun.data

import android.content.Context
import com.application.intervalrun.data.database.AppDatabase
import com.application.intervalrun.data.repository.TrainingCycleRepository

interface AppDataWrapper {
    val trainingCycleRepository: TrainingCycleRepository
}
class AppDataContainer(private val context: Context) : AppDataWrapper {

    override val trainingCycleRepository: TrainingCycleRepository by lazy {
        TrainingCycleRepository(AppDatabase.getDatabase(context).trainingCycleDao())
    }
}