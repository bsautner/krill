package io.github.bsautner.krill.pi

import kotlinx.serialization.Serializable

@Serializable
data class GpioPin(
    val number: Int,
    val name: String,
    val mode: String? = "IN", // or "OUT" or null if not io
    val state: String? = "HIGH", // Low or null of not io
    val isConfigurable: Boolean = true,
    val initialState: Boolean = false
)


val headerJsonAll = """
    [
      { "number": 1, "name": "3.3V", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 2, "name": "5V", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 3, "name": "GPIO2", "mode": "IN", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 4, "name": "5V", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 5, "name": "GPIO3", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 6, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 7, "name": "GPIO4", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 8, "name": "GPIO14", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 9, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 10, "name": "GPIO15", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 11, "name": "GPIO17", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 12, "name": "GPIO18", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 13, "name": "GPIO27", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 14, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 15, "name": "GPIO22", "mode": "IN", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 16, "name": "GPIO23", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 17, "name": "3.3V", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 18, "name": "GPIO24", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 19, "name": "GPIO10 (MOSI)", "mode": "IN", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 20, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 21, "name": "GPIO9 (MISO)", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 22, "name": "GPIO25", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 23, "name": "GPIO11", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 24, "name": "GPIO8 (CE0)", "mode": "IN", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 25, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 26, "name": "GPIO7 (CE1)", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 27, "name": "ID_SD (EEPROM SDA)", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 28, "name": "ID_SC (EEPROM SCL)", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 29, "name": "GPIO5", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 30, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 31, "name": "GPIO6", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 32, "name": "GPIO12", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 33, "name": "GPIO13", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 34, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 35, "name": "GPIO19", "mode": "IN", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 36, "name": "BCM16", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 37, "name": "GPIO26", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 38, "name": "GPIO20", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 39, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 40, "name": "GPIO21", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true }
    ]

""".trimIndent()


val headerJson = """
    [
      { "number": 29, "name": "GPIO5", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 30, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 31, "name": "GPIO6", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 32, "name": "GPIO12", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 33, "name": "GPIO13", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 34, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 35, "name": "GPIO19", "mode": "IN", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 36, "name": "BCM16", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true },
      { "number": 37, "name": "GPIO26", "mode": "OUT", "state": "LOW", "isConfigurable": true, "initialState": false },
      { "number": 38, "name": "GPIO20", "mode": "IN", "state": "HIGH", "isConfigurable": true, "initialState": false },
      { "number": 39, "name": "GND", "mode": null, "state": null, "isConfigurable": false, "initialState": false },
      { "number": 40, "name": "GPIO21", "mode": "OUT", "state": "HIGH", "isConfigurable": true, "initialState": true }
    ]

""".trimIndent()