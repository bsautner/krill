package io.github.bsautner.krill.pi

import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import io.github.bsautner.krill.client.myJson

class PiManager(private val pcp: PiContextProvider) {

    fun togglePin() {
         val pin = pcp.getPin()

        if (pin.isLow) {
            pin.high()
        } else {
            pin.low()
        }



    }

    fun getPin() : DigitalOutput {
        return pcp.getPin()
    }

    fun getPins() : List<GpioPin> {
        return pcp.getPins()
    }

}



class PiContextProvider  {
    private lateinit var pi : Context
    private lateinit var bcm16: DigitalOutput
    private lateinit var gpioHeader : List<GpioPin>

    init {
        if (! ::pi.isInitialized) {
            pi = Pi4J.newAutoContext()
            gpioHeader = myJson.decodeFromString(headerJson)
        }
    }

    fun getPins() : List<GpioPin> {

        val reg = pi.registry().allByType(DigitalOutput::class.java)

        println("getPins ${reg.size}")

        val pin = getPin()
        pin.addListener()
        println(pin.state())
        return gpioHeader
    }

    fun getPin() : DigitalOutput {
        if (! ::bcm16.isInitialized) {
            bcm16 = pi.create(DigitalOutput.newConfigBuilder(pi)
                .id("BCM16")
                .name("LED #16")
                .address(16)
                // .provider(pi.providers().digitalOutput())
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
            )
        }
        return bcm16

    }


}
