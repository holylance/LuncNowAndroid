package com.cockerspaniel.network.base.util

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun String.toZonedDateTime(): ZonedDateTime = ZonedDateTime.parse(this)

fun String.toZonedDateTimeWithFormatter(): ZonedDateTime {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        .withZone(ZoneId.systemDefault())
    return ZonedDateTime.parse(this, formatter)
}
