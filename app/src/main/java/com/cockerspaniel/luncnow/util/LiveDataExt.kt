package com.cockerspaniel.luncnow.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun<T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}
