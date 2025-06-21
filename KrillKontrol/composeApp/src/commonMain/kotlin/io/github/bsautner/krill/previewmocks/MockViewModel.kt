package io.github.bsautner.krill.previewmocks

import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.client.Screen
import io.github.bsautner.krill.client.myJson
import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MockViewModel()  : KrillOperations {

    private val _pins = MutableStateFlow<List<GpioPin>>(emptyList())
    override val pins: StateFlow<List<GpioPin>> = _pins
    private val _selectedPin = MutableStateFlow<GpioPin?>(null)
    override val selectedPin: MutableStateFlow<GpioPin?> = _selectedPin
    private val _screen = MutableStateFlow(Screen.MAIN)
    override val screen: MutableStateFlow<Screen> = _screen
    override suspend fun updatePin(pin: GpioPin) {
        TODO("Not yet implemented")
    }


    init {
        val pins = myJson.decodeFromString<List<GpioPin>>(headerJson)
        _pins.update {  pins }


    }


    override suspend fun start(callback: suspend (GpioPin) -> Unit) {
        TODO("Not yet implemented")
    }

    companion object {
        val headerJson = """
    [{"number":1,"name":"3.3V","mode":null,"state":null,"isConfigurable":false},{"number":2,"name":"5V","mode":null,"state":null,"isConfigurable":false},{"number":3,"name":"GPIO2","state":"LOW"},{"number":4,"name":"5V","mode":null,"state":null,"isConfigurable":false},{"number":5,"name":"GPIO3","state":"LOW"},{"number":6,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":7,"name":"GPIO4","mode":"OUT","state":"LOW","initialState":true},{"number":8,"name":"GPIO14","mode":"OUT","state":"LOW"},{"number":9,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":10,"name":"GPIO15","state":"LOW"},{"number":11,"name":"GPIO17","mode":"OUT","state":"LOW"},{"number":12,"name":"GPIO18","state":"LOW"},{"number":13,"name":"GPIO27","mode":"OUT","state":"LOW","initialState":true},{"number":14,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":15,"name":"GPIO22","state":"LOW"},{"number":16,"name":"GPIO23","mode":"OUT","state":"LOW","initialState":true},{"number":17,"name":"3.3V","mode":null,"state":null,"isConfigurable":false},{"number":18,"name":"GPIO24","mode":"OUT","state":"LOW"},{"number":19,"name":"GPIO10","state":"LOW"},{"number":20,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":21,"name":"GPIO9","mode":"OUT","state":"LOW","initialState":true},{"number":22,"name":"GPIO25","mode":"OUT","state":"LOW"},{"number":23,"name":"GPIO11","state":"LOW"},{"number":24,"name":"GPIO8","state":"LOW"},{"number":25,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":26,"name":"GPIO7","mode":"OUT","state":"LOW","initialState":true},{"number":27,"name":"ID_SD","mode":null,"state":null,"isConfigurable":false},{"number":28,"name":"ID_SC","mode":null,"state":null,"isConfigurable":false},{"number":29,"name":"GPIO5","mode":"OUT","state":"LOW"},{"number":30,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":31,"name":"GPIO6","state":"LOW"},{"number":32,"name":"GPIO12","mode":"OUT","state":"LOW"},{"number":33,"name":"GPIO13","mode":"OUT","state":"LOW","initialState":true},{"number":34,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":35,"name":"GPIO19","state":"LOW"},{"number":36,"name":"GPIO16","mode":"OUT","state":"LOW","initialState":true},{"number":37,"name":"GPIO26","mode":"OUT","state":"LOW"},{"number":38,"name":"GPIO20","state":"LOW"},{"number":39,"name":"GND","mode":null,"state":null,"isConfigurable":false},{"number":40,"name":"GPIO21","mode":"OUT","state":"LOW","initialState":true}]
    """.trimIndent()
    }


}