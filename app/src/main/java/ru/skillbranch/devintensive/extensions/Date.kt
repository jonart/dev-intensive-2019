package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var differenceSeconds: Int = ((Date().time - this.time) / 1000).toInt()

    return when{
        differenceSeconds >= 0 ->{
            when {
                differenceSeconds < 60 -> {
                    when (differenceSeconds) {
                        0, 2 -> "только что"
                        in 2..45 -> "несколько секунд назад"
                        else -> "минуту назад"
                    }
                }
                (differenceSeconds / 60) < 60 -> {
                    when (val minutes = (differenceSeconds / 60)) {
                        1, 21, 31, 41, 51 -> "$minutes минута назад"
                        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "$minutes минуты назад"
                        else -> "$minutes минут назад"
                    }
                }
                (differenceSeconds / 3600) < 24 -> {
                    when (val hour = (differenceSeconds / 3600)) {
                        1, 21 -> "$hour час назад"
                        2, 3, 4, 22, 23 -> "$hour часа назад"
                        else -> "$hour часов назад"
                    }
                }
                (differenceSeconds / 86400) < 364 -> {
                    when (val days = (differenceSeconds / 86400)) {
                        1, 21, 31, 41, 51, 61, 71, 81, 91, 101, 121, 131, 141, 151, 161, 171, 181, 191, 201, 221, 231, 241, 251, 261, 271, 281, 291, 301, 321, 331, 341, 351, 361 -> "$days день назад"
                        2, 3, 4,
                        22, 23, 24,
                        32, 33, 34,
                        42, 43, 44,
                        52, 53, 54,
                        62, 63, 64,
                        72, 73, 74,
                        82, 83, 84,
                        92, 93, 94,
                        102, 103, 104,
                        122, 123, 124,
                        132, 133, 134,
                        142, 143, 144,
                        152, 153, 154,
                        162, 163, 164,
                        172, 173, 174,
                        182, 183, 184,
                        192, 193, 194,
                        202, 203, 204,
                        222, 223, 224,
                        232, 233, 234,
                        242, 243, 244,
                        252, 253, 254,
                        262, 263, 264,
                        272, 273, 274,
                        282, 283, 284,
                        292, 293, 294,
                        302, 303, 304,
                        322, 323, 324,
                        332, 333, 334,
                        342, 343, 344,
                        352, 353, 354,
                        362, 363, 364
                        -> "$days дня назад"
                        else -> "более года назад"
                    }
                }
                else -> {
                    (differenceSeconds / 86400).toString() + " дней назад"
                }
            }
        }
        else -> {
            differenceSeconds = (-differenceSeconds) +1
            when {
                differenceSeconds < 60 -> {
                    when (differenceSeconds) {
                        0, 2 -> "только что"
                        in 2..45 -> "через несколько секунд"
                        else -> "через минуту"
                    }
                }
                (differenceSeconds / 60) < 60 -> {
                    when (val minutes = (differenceSeconds / 60)) {
                        1, 21, 31, 41, 51 -> "через  $minutes минут"
                        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "через  $minutes минуты"
                        else -> "через $minutes минут"
                    }
                }
                (differenceSeconds / 3600) < 24 -> {
                    when (val hour = (differenceSeconds / 3600)) {
                        1, 21 -> "через $hour час"
                        2, 3, 4, 22, 23 -> "через $hour часа"
                        else -> "через $hour часов"
                    }
                }
                (differenceSeconds / 86400) < 364 -> {
                    when (val days = (differenceSeconds / 86400)) {
                        1, 21, 31, 41, 51, 61, 71, 81, 91, 101, 121, 131, 141, 151, 161, 171, 181, 191, 201, 221, 231, 241, 251, 261, 271, 281, 291, 301, 321, 331, 341, 351, 361 -> "через $days день"
                        2, 3, 4,
                        22, 23, 24,
                        32, 33, 34,
                        42, 43, 44,
                        52, 53, 54,
                        62, 63, 64,
                        72, 73, 74,
                        82, 83, 84,
                        92, 93, 94,
                        102, 103, 104,
                        122, 123, 124,
                        132, 133, 134,
                        142, 143, 144,
                        152, 153, 154,
                        162, 163, 164,
                        172, 173, 174,
                        182, 183, 184,
                        192, 193, 194,
                        202, 203, 204,
                        222, 223, 224,
                        232, 233, 234,
                        242, 243, 244,
                        252, 253, 254,
                        262, 263, 264,
                        272, 273, 274,
                        282, 283, 284,
                        292, 293, 294,
                        302, 303, 304,
                        322, 323, 324,
                        332, 333, 334,
                        342, 343, 344,
                        352, 353, 354,
                        362, 363, 364
                        -> "через $days дня"
                        else -> "более чем через год"
                    }
                }
                else -> {
                    "более чем через год"
                }
            }
        }
    }

}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}