package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

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
    val isFuture:Boolean

    isFuture = differenceSeconds < 0
    differenceSeconds = abs(differenceSeconds)

    if(isFuture){
        return when{
            differenceSeconds == 0 || differenceSeconds == 1 -> { "только что" }
            differenceSeconds in 1..45 -> "через несколько секунд"
            differenceSeconds in 45..75 -> "через минуту"
            toMinutes(differenceSeconds) < 45  -> "через " + TimeUnits.MINUTE.plural(toMinutes(differenceSeconds))
            toMinutes(differenceSeconds) in 45..75 -> "через час"
            toHours(differenceSeconds) < 22  -> "через " + TimeUnits.HOUR.plural(toHours(differenceSeconds))
            toHours(differenceSeconds) in 22..26 -> "через день"
            toHours(differenceSeconds) > 26 && toDays(differenceSeconds) < 360 -> "через " + TimeUnits.DAY.plural(toDays(differenceSeconds))
            else -> "более чем через год"}
    }
    else{
        return when{
            differenceSeconds == 0 || differenceSeconds == 1 -> { "только что" }
            differenceSeconds in 1..45 -> "несколько секунд назад"
            differenceSeconds in 45..75 -> "минуту назад"
            toMinutes(differenceSeconds) < 45  -> TimeUnits.MINUTE.plural(toMinutes(differenceSeconds)) + " назад"
            toMinutes(differenceSeconds) in 45..75 -> "час назад"
            toHours(differenceSeconds) < 22  -> TimeUnits.HOUR.plural(toHours(differenceSeconds)) + " назад"
            toHours(differenceSeconds) in 22..26 -> "день назад"
            toHours(differenceSeconds) > 26 && toDays(differenceSeconds) < 360 -> TimeUnits.DAY.plural(toDays(differenceSeconds)) + " назад"
            else -> "более года назад"}
    }
}

private fun toDays(differenceSeconds: Int) = differenceSeconds / 86400
private fun toHours(differenceSeconds: Int) = differenceSeconds / 3600
private fun toMinutes(differenceSeconds: Int) = differenceSeconds / 60




enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value:Int):String {
        return when{
            SECOND == this -> {
                when{
                    value % 100 in 11..19 -> "$value секунд".format(value)
                    value % 10 == 1 -> "$value секунду".format(value)
                    value % 10 in 2..4 -> "$value секунды".format(value)
                    else -> "$value секунд".format(value)
                }
            }
            MINUTE == this -> {
                when{
                    value % 100 in 11..19 -> "$value минут".format(value)
                    value % 10 == 1 -> "$value минуту".format(value)
                    value % 10 in 2..4 -> "$value минуты".format(value)
                    else -> "$value минут".format(value)
                }
            }
            HOUR== this -> {
                when{
                    value % 100 in 11..19 -> "$value часов".format(value)
                    value % 10 == 1 -> "$value час".format(value)
                    value % 10 in 2..4 -> "$value часа".format(value)
                    else -> "$value часов".format(value)
                }
            }
            DAY == this -> {
                when{
                    value % 100 in 11..19 -> "$value дней".format(value)
                    value % 10 == 1 -> "$value день".format(value)
                    value % 10 in 2..4 -> "$value дня".format(value)
                    else -> "$value дней".format(value)
                }
            }
            else -> "not correct"
        }
    }
}