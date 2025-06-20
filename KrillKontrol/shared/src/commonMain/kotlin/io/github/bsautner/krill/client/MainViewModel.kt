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

class KrillApiClient(private val client: KrillClient) {
    suspend fun testGet() {
        client.testGet()
    }

    suspend fun start(callback : suspend (GpioPin) -> Unit) {
        client.start(callback)
    }
}

open class MainViewModel(private val apiClient: KrillApiClient) : KrillViewModel  {
    private val _pins = MutableStateFlow<List<GpioPin>>(emptyList())
    override val pins: StateFlow<List<GpioPin>> = _pins

    init {
          val pins = myJson.decodeFromString<List<GpioPin>>(headerJson)
        _pins.update {  pins }

    }

    override suspend fun getPinStatus(): List<GpioPin> {
        TODO("Not yet implemented")
    }



    override suspend fun onButtonClick() {
        scope.launch {
            val result = apiClient.testGet()
            println(result)
        }
    }

    override suspend fun start() {
        apiClient.start { updatedPin ->
            println("got callback $updatedPin")
            _pins.value.forEach { pin ->
                println("--- $pin")

            }
            _pins.update { current ->
                println("$current -> $updatedPin")
                current.map { if (it.name == updatedPin.name) updatedPin else it }
            }
        }
    }
}

interface KrillViewModel {
    val pins: StateFlow<List<GpioPin>>
    suspend fun getPinStatus() : List<GpioPin>
    suspend fun onButtonClick()
    suspend fun start()

}