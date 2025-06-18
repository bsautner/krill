package io.github.bsautner.krill.pi

import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState

class PiManager(private val pcp: PiContextProvider) {

    fun togglePin() {
         val pin = pcp.getPin()

        if (pin.isLow) {
            pin.high()
        } else {
            pin.low()
        }



    }

}



class PiContextProvider  {
    private lateinit var pi : Context
    private lateinit var bcm16: DigitalOutput

    init {
        if (! ::pi.isInitialized) {
            pi = Pi4J.newAutoContext()
        }
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
