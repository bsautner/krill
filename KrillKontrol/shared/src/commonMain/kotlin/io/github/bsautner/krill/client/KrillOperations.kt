package io.github.bsautner.krill.client

import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.StateFlow

interface KrillOperations {

    val pins: StateFlow<List<GpioPin>>
    suspend fun testGet() :String
    suspend fun getPinStatus() : List<GpioPin>
    suspend fun start(callback : suspend (GpioPin) -> Unit)



}