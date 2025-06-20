package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.bsautner.krill.client.KrillOperations

import io.github.bsautner.krill.di.initKoin

fun main() = application {
    val koin = initKoin()
    val vm: KrillOperations = koin.get()
    Window(
        onCloseRequest = ::exitApplication,
        title = "KrillKontrol",
    ) {
        App(vm)
    }
}