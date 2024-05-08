package com.application.intervalrun.util

import android.util.Log

object TimeFormatUtil {
    private val acceptableEndCharArray =
        arrayOf('s', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

    fun formatDurationInNumberToHMS(duration: Int): String {
        Log.d("TimeFormatUtil", "duration: $duration")
        val hours = duration / 10000
        val minutes = (duration % 10000) / 100
        val seconds = duration % 100
        return String.format("%02dh:%02dm:%02ds", hours, minutes, seconds)
    }

    fun formatDurationFromHMSToNumber(durationText: String): Int {
        val (hours, minutes, seconds) =  durationText
            .trim()
            .dropLastWhile { it !in acceptableEndCharArray }
            .split(':')
            .map { it.filter { char -> char.isDigit() } }
        return (hours + minutes + seconds).toInt()
    }
}