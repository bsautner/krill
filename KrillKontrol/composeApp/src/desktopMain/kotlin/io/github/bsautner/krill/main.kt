package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.gihub.bsautner.krill.client.KrillViewModel
import io.gihub.bsautner.krill.di.initKoin

fun main() = application {
    val koin = initKoin()
    val vm: KrillViewModel = koin.get()
    Window(
        onCloseRequest = ::exitApplication,
        title = "KrillKontrol",
    ) {
        App(vm)
    }
}