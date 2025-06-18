package org.example.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.gihub.bsautner.client.DefaultKrillClient
import io.gihub.bsautner.client.MainViewModel
import io.gihub.bsautner.client.TestGetUseCase
import io.gihub.bsautner.client.getHttpClient
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val client = getHttpClient().client
    val krillClient = DefaultKrillClient(client)
    val test = TestGetUseCase(krillClient)
    val vm = MainViewModel(test)

    ComposeViewport(document.body!!) {
        App(vm)
    }
}

