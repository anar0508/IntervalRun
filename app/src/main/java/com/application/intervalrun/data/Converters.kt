package com.application.intervalrun.data

import androidx.room.TypeConverter
import com.application.intervalrun.model.Timer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromTimerList(timersList: MutableList<Timer>?): String {
        val gson = Gson()
        return gson.toJson(timersList)
    }

    @TypeConverter
    fun toTimerList(value: String): MutableList<Timer>? {
        val gson = Gson()
        val type = object : TypeToken<MutableList<Timer>>() {}.type
        return gson.fromJson(value, type)
    }
}