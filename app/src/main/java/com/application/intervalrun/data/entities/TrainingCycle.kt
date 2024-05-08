package com.application.intervalrun.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.application.intervalrun.enums.CycleState
import com.application.intervalrun.model.Interval


@Entity (tableName = "training_cycle")
data class TrainingCycle(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "cycle_name") val cycleName: String,
    @ColumnInfo(name = "timers") val intervals: MutableList<Interval>?,
    @ColumnInfo(name = "cycleState") val cycleState: CycleState = CycleState.STOPPED,
    @ColumnInfo(name = "isTemplate") val isTemplate: Boolean = false,
)