package io.github.bsautner.krill.pi

import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import io.github.bsautner.krill.client.myJson
import java.io.File

class PiManager(private val pcp: PiContextProvider) {

    fun setPinState(pin: GpioPin) {

         val piPin = pcp.getPin(pin)

         when (pin.state) {
             State.NA -> {}
             State.LOW -> {
                 piPin.low()
             }
             State.HIGH -> {
                 piPin.high()
             }
         }




    }

    fun getPin(pin: GpioPin) : DigitalOutput {
        return pcp.getPin(pin)
    }

    fun getPins(): List<DigitalOutput> {
        return pcp.getPins()
    }



}



class PiContextProvider  {
    private lateinit var pi : Context
    private var pins: MutableList<DigitalOutput>  = mutableListOf()
    private lateinit var gpioHeader : List<GpioPin>

    init {
        if (! ::pi.isInitialized) {
            pi = Pi4J.newAutoContext()


        }
    }


    fun getPin(pin: GpioPin) : DigitalOutput {
        if  (pins.isEmpty())   {
            loadPins()
        }
        return pins.find { it.name == pin.name }!!


    }

    fun getPins(): List<DigitalOutput> {
        if  (pins.isEmpty())   {
            loadPins()
        }
        return pins
    }

    fun loadPins() {
        val json = File("/etc/krill/gpio.json")
        if (json.exists()) {
            gpioHeader = myJson.decodeFromString<List<GpioPin>>(json.readText())
            pins =  mutableListOf()
            gpioHeader.forEach {
                if (it.mode == "OUT") {
                    try {
                        pins.add(
                            pi.create(
                                DigitalOutput.newConfigBuilder(pi)
                                    .id(it.name)
                                    .name(it.name)
                                    .address(it.number)
                                    // .provider(pi.providers().digitalOutput())
                                    .shutdown(DigitalState.LOW)
                                    .initial(DigitalState.LOW)
                            )
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
        else {
            throw IllegalStateException("The header file could not be loaded ${json.path}")
        }
    }


}
