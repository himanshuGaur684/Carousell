package com.core.common

import java.util.*

const val ONE_SECONDS_IN_MILLIS = 1000L
const val ONE_MINUTE_IN_MILLIS = 60 * 1000L
const val ONE_HOUR_IN_MILLIS = 3600 * 1000L
const val ONE_DAY_IN_MILLIS = 86400000L
const val ONE_WEEK_IN_MILLIS = ONE_DAY_IN_MILLIS * 7L
const val ONE_MONTH_IN_MILLIS = ONE_WEEK_IN_MILLIS * 4L
const val ONE_YEAR_IN_MILLIS = ONE_MONTH_IN_MILLIS * 12L


fun differenceOfDateFromCurrentDate(time: Int): String {
    val currentDate = currentTimeInMillis()
    val givenDate = convertEpochInMillis(time)

    val timeDifference = currentDate.minus(givenDate)

    return when {
         timeDifference.isLessThanOneMinute() -> {
            val seconds = timeDifference.div(ONE_SECONDS_IN_MILLIS)
            seconds.toInt().toString() + if (seconds == 1L) " second ago" else " seconds ago"
        }
        timeDifference.isLessThanOneHourAndAboveFiftyNineSeconds() -> {
            val minutes = timeDifference.div(ONE_MINUTE_IN_MILLIS)
            minutes.toInt().toString() + if (minutes == 1L) " minute ago" else " minutes ago"
        }
        timeDifference.isLessThanOneDayAndAboveFiftyNineMinutes() -> {
            val hours = timeDifference.div(ONE_HOUR_IN_MILLIS)
            hours.toInt().toString() + if (hours == 1L) " hour ago" else " hours ago"
        }
        timeDifference.isLessThanSevenDaysAndAboveTwentyThreeHours() -> {
            val days = timeDifference.div(ONE_DAY_IN_MILLIS)
            timeDifference.div(ONE_DAY_IN_MILLIS).toInt()
                .toString() + if (days == 1L) " day ago" else " days ago"
        }
        timeDifference.isLessThanFourWeeksAndAboveSixDays() -> {
            val weeks = timeDifference.div(ONE_WEEK_IN_MILLIS)
            weeks.toInt().toString() + if (weeks == 1L) " week ago" else " weeks ago"
        }
        timeDifference.isLessThanTwelveMonthsAndAboveThreeWeeks() -> {
            val months = timeDifference.div(ONE_MONTH_IN_MILLIS)
            months.toInt().toString() + if (months == 1L) " month ago" else " months ago"
        }
        timeDifference.isLessThanInfiniteMonthsAndAboveTwelveMonths() -> {
            val years = timeDifference.div(ONE_YEAR_IN_MILLIS)
            years.toInt().toString() + if (years == 1L) " year ago" else " years ago"
        }
        else -> {
            ""
        }
    }
}

fun Long.isLessThanOneMinute() = div(ONE_SECONDS_IN_MILLIS) < 60L

fun Long.isLessThanOneHourAndAboveFiftyNineSeconds() = div(ONE_MINUTE_IN_MILLIS) in 1L..59L

fun Long.isLessThanOneDayAndAboveFiftyNineMinutes() = div(ONE_HOUR_IN_MILLIS) in 1L..23L

fun Long.isLessThanSevenDaysAndAboveTwentyThreeHours() = div(ONE_DAY_IN_MILLIS) in 1L..6L

fun Long.isLessThanFourWeeksAndAboveSixDays() = div(ONE_WEEK_IN_MILLIS) in 1L..3L

fun Long.isLessThanTwelveMonthsAndAboveThreeWeeks() = div(ONE_MONTH_IN_MILLIS) in 1L..11L

fun Long.isLessThanInfiniteMonthsAndAboveTwelveMonths() = div(ONE_YEAR_IN_MILLIS) in 1L..Long.MAX_VALUE

fun getYesterdayTimeEpoch():Int{
    val currentDate = currentTimeInMillis()
    val previousDayTimeInMillis = currentDate.minus(ONE_DAY_IN_MILLIS).div(1000)
    return previousDayTimeInMillis.toInt()
}

fun currentTimeInMillis() = Date().time

fun convertEpochInMillis(millis: Int) = Date(millis.toLong() * 1000).time