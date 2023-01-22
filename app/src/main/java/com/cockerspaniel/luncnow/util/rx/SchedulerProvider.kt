package com.cockerspaniel.luncnow.util.rx

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler

    fun computation(): Scheduler
}
