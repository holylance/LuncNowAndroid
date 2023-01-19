package com.cockerspaniel.luncnow.util

/**
 * Runs the block if object is null.
 */
inline fun <T> T?.ifNull(block: () -> Unit): T? {
    if (this == null) block()
    return this
}
