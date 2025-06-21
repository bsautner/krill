package io.github.bsautner.krill.pi

import kotlinx.serialization.Serializable

@Serializable
data class GpioPin(
    val number: Int,
    val name: String,
    val mode: String? = "IN", // or "OUT" or null if not io
    var state: State = State.NA,
    val isConfigurable: Boolean = true,
    val initialState: Boolean = false
) {


    fun toggleState() {
        when (state) {
            State.HIGH -> this.state = State.LOW
            State.LOW -> this.state = State.HIGH
            State.NA -> this.state = State.NA
        }


    }
}

@Serializable
enum class State {
    HIGH, LOW, NA
}