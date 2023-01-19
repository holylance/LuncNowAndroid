package com.cockerspaniel.luncnow.util

/**
 * Wrapper for generic view states: success, empty state, and error. The class is
 * meant to be used for ViewModel data values, to enforce proper empty and error
 * state handling in Fragments.
 */
sealed class ViewState<out T> {
    data class Init<T>(val value: T) : ViewState<T>()

    object Loading : ViewState<Nothing>()

    data class Success<T>(val value: T) : ViewState<T>()

    object Empty : ViewState<Nothing>()

    data class Error(val cause: Throwable) : ViewState<Nothing>()
}

fun <T> T.asViewState(): ViewState<T> {
    return if (this is Collection<*> && isEmpty()) {
        ViewState.Empty
    } else {
        ViewState.Success(this)
    }
}

fun <T> Throwable.asViewState(): ViewState<T> {
    return ViewState.Error(this)
}
