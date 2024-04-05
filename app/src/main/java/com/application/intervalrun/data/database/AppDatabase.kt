package com.application.intervalrun.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.application.intervalrun.data.Converters
import com.application.intervalrun.data.entities.TrainingCycle
import com.application.intervalrun.data.dao.TrainingCycleDao


@Database(entities=[TrainingCycle::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun trainingCycleDao (): TrainingCycleDao
    companion object {

        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                     .build()
                    .also { Instance = it }
            }
        }
    }
}