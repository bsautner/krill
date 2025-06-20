package org.example.project

import androidx.lifecycle.ViewModel
import io.github.bsautner.krill.client.KrillViewModel
import io.github.bsautner.krill.client.MainViewModel
import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.StateFlow

class MainAndroidViewModel(private val mainViewModel: MainViewModel):  ViewModel(), KrillViewModel {
    override val pins: StateFlow<List<GpioPin>>
        get() = TODO("Not yet implemented")

    override suspend fun getPinStatus(): List<GpioPin> {
        TODO("Not yet implemented")
    }

    override suspend fun onButtonClick() {
        mainViewModel.onButtonClick()
    }

    override suspend fun start() {
        TODO("Not yet implemented")
    }


}