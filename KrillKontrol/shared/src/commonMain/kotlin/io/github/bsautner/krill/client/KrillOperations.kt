package io.github.bsautner.krill.client

import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface KrillOperations {

    val pins: StateFlow<List<GpioPin>>
    val selectedPin: MutableStateFlow<GpioPin?>
    val screen: MutableStateFlow<Screen>

    suspend fun updatePin(pin: GpioPin)
    suspend fun start(callback : suspend (GpioPin) -> Unit)



}

enum class Screen {
    MAIN, CONFIGURE_PIN
}