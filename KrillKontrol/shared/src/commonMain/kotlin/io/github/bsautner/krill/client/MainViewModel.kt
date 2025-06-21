package io.github.bsautner.krill.client

import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


open class MainViewModel(private val apiClient: KrillKtorClient) : KrillOperations  {

    private val _pins = MutableStateFlow<List<GpioPin>>(emptyList())
    override val pins: StateFlow<List<GpioPin>> = _pins

    private val _selectedPin = MutableStateFlow<GpioPin?>(null)
    override val selectedPin: MutableStateFlow<GpioPin?> = _selectedPin

    private val _screen = MutableStateFlow(Screen.MAIN)
    override val screen: MutableStateFlow<Screen> = _screen


    override suspend fun updatePin(pin: GpioPin) {
         apiClient.updatePin(pin)
    }

    override suspend fun start(callback : suspend (GpioPin) -> Unit) {
        apiClient.start { updatedPin ->
            println("got callback $updatedPin")
              _pins.update { current ->
                   current.map { if (it.name == updatedPin.name) updatedPin else it }
            }
        }
        val pins = apiClient.getHeader()
        _pins.update { pins }

    }
}

