package org.example.project

import androidx.lifecycle.ViewModel
import io.github.bsautner.krill.client.KrillOperations

import io.github.bsautner.krill.client.MainViewModel
import io.github.bsautner.krill.client.Screen
import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainAndroidViewModel(private val mainViewModel: MainViewModel):  ViewModel(), KrillOperations {
    override val pins: StateFlow<List<GpioPin>>
        get() = mainViewModel.pins

    override val selectedPin: MutableStateFlow<GpioPin?>
        get() = mainViewModel.selectedPin

    override val screen: MutableStateFlow<Screen>
        get() = mainViewModel.screen

    override suspend fun updatePin(pin: GpioPin) {
         mainViewModel.updatePin(pin)
    }



    override suspend fun start(callback : suspend (GpioPin) -> Unit) {
       mainViewModel.start(callback)
    }


}