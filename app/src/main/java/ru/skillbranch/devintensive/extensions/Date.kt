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
        when{
            differenceSeconds < 60  -> return getQuantityText(
                number = differenceSeconds,
                oneFormat = "через %d секунду",
                fewFormat = "через %d секунды",
                manyFormat = "через %d секунд"
            )
            toMinutes(differenceSeconds) < 60  -> return getQuantityText(
                number =  toMinutes(differenceSeconds),
                oneFormat = "через %d минуту",
                fewFormat = "через %d минуты",
                manyFormat = "через %d минут"
            )
            toHours(differenceSeconds) < 24  -> return getQuantityText(
                number = toHours(differenceSeconds),
                oneFormat = "через %d час",
                fewFormat = "через %d часа",
                manyFormat = "через %d часов"
            )
            else ->
                return getQuantityText(
                    number = toDays(differenceSeconds),
                    oneFormat = "через %d день",
                    fewFormat = "через %d дня",
                    manyFormat = "через %d дней")
        }
    }
    else{
        when{
            differenceSeconds == 0 || differenceSeconds == 1 -> {
                return "только что"
            }
            differenceSeconds in 1..45 -> return "несколько секунд назад"

            differenceSeconds in 45..75 -> return "минуту назад"

            toMinutes(differenceSeconds) < 45  -> return getQuantityText(
                number =  toMinutes(differenceSeconds),
                oneFormat = "%d минуту назад",
                fewFormat = "%d минуты назад",
                manyFormat = "%d минут назад"
            )

            toMinutes(differenceSeconds) in 45..75 -> return "час назад"

            toHours(differenceSeconds) < 22  -> return getQuantityText(
                number = toHours(differenceSeconds),
                oneFormat = "%d час назад",
                fewFormat = "%d часа назад",
                manyFormat = "%d часов назад"
            )

            toHours(differenceSeconds) in 22..26 -> return "день назад"
            toHours(differenceSeconds) > 26 && toDays(differenceSeconds) < 360 -> return getQuantityText(
                number = toDays(differenceSeconds),
                oneFormat = "%d день назад",
                fewFormat = "%d дня назад",
                manyFormat = "%d дней назад")

            else ->
                return "более года назад"
        }
    }
}

private fun toDays(differenceSeconds: Int) = differenceSeconds / 86400
private fun toHours(differenceSeconds: Int) = differenceSeconds / 3600
private fun toMinutes(differenceSeconds: Int) = differenceSeconds / 60

fun getQuantityText(number: Int, oneFormat: String, fewFormat: String, manyFormat: String):String {
    return when{
        number % 100 in 11..19 -> manyFormat.format(number)
        number % 10 == 1 -> oneFormat.format(number)
        number % 10 in 2..4 -> fewFormat.format(number)
        else -> manyFormat.format(number)
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}