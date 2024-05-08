package com.application.intervalrun.data

import androidx.room.TypeConverter
import com.application.intervalrun.model.Interval
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromIntervalList(timersList: MutableList<Interval>?): String {
        val gson = Gson()
        return gson.toJson(timersList)
    }

    @TypeConverter
    fun toIntervalList(value: String): MutableList<Interval>? {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Interval>>() {}.type
        return gson.fromJson(value, type)
    }
}