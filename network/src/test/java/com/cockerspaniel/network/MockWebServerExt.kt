package com.cockerspaniel.network

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.logging.Level
import java.util.logging.Logger

fun MockWebServer.enqueueSilently(response: MockResponse) {
    Logger.getLogger(this::class.java.name).level = Level.WARNING
    enqueue(response)
}
