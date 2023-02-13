package com.cockerspaniel.network.base.util

import java.time.ZonedDateTime

fun String.toZonedDateTime(): ZonedDateTime = ZonedDateTime.parse(this)
