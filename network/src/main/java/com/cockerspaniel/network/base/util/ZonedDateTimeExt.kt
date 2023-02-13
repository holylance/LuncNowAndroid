package com.cockerspaniel.network.base.util

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun ZonedDateTime.toParsedString(): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        .withZone(ZoneId.systemDefault())
    return this.format(formatter)
}
