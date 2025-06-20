package io.github.bsautner.krill.client

import io.github.bsautner.krill.pi.GpioPin
import io.github.bsautner.krill.pi.headerJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


private val viewModelJob = SupervisorJob()
private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)


open class MainViewModel(private val apiClient: DefaultKrillClient) : KrillOperations  {
    private val _pins = MutableStateFlow<List<GpioPin>>(emptyList())
    override val pins: StateFlow<List<GpioPin>> = _pins


    override suspend fun testGet(): String {
        return apiClient.testGet()
    }

    init {
          val pins = myJson.decodeFromString<List<GpioPin>>(headerJson)
        _pins.update {  pins }

    }

    override suspend fun getPinStatus(): List<GpioPin> {
        TODO("Not yet implemented")
    }

    override suspend fun start(callback : suspend (GpioPin) -> Unit) {
        apiClient.start { updatedPin ->
              _pins.update { current ->
                   current.map { if (it.name == updatedPin.name) updatedPin else it }
            }
        }
    }
}

