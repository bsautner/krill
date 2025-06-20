package org.example.project

import androidx.lifecycle.ViewModel
import io.github.bsautner.krill.client.KrillOperations

import io.github.bsautner.krill.client.MainViewModel
import io.github.bsautner.krill.pi.GpioPin
import kotlinx.coroutines.flow.StateFlow

class MainAndroidViewModel(private val mainViewModel: MainViewModel):  ViewModel(), KrillOperations {
    override val pins: StateFlow<List<GpioPin>>
        get() = mainViewModel.pins

    override suspend fun testGet(): String {
        return mainViewModel.testGet()
    }

    override suspend fun getPinStatus(): List<GpioPin> {
        return mainViewModel.getPinStatus()
    }

    override suspend fun start(callback : suspend (GpioPin) -> Unit) {
       mainViewModel.start(callback)
    }


}