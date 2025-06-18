package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.gihub.bsautner.client.DefaultKrillClient
import io.gihub.bsautner.client.MainViewModel
import io.gihub.bsautner.client.TestGetUseCase
import io.gihub.bsautner.client.getHttpClient

fun main() = application {
    val client = getHttpClient().client
    val krillClient = DefaultKrillClient(client)
    val test = TestGetUseCase(krillClient)
    val vm = MainViewModel(test)
    Window(
        onCloseRequest = ::exitApplication,
        title = "KrillKontrol",
    ) {
        App(vm)
    }
}