package org.example.project

import androidx.lifecycle.ViewModel
import io.github.bsautner.krill.client.KrillViewModel
import io.github.bsautner.krill.client.MainViewModel
import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.StateFlow

class MainAndroidViewModel(private val mainViewModel: MainViewModel):  ViewModel(), KrillViewModel {
    override val pins: StateFlow<List<GpioPin>>
        get() = mainViewModel.pins

    override suspend fun getPinStatus(): List<GpioPin> {
        return mainViewModel.getPinStatus()
    }

    override suspend fun onButtonClick() {
        mainViewModel.onButtonClick()
    }

    override suspend fun start() {
       mainViewModel.start()
    }


}