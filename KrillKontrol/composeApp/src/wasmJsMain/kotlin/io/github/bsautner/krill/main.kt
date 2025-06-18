package org.example.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.gihub.bsautner.krill.client.MainViewModel
import io.gihub.bsautner.krill.di.initKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val koin = initKoin()
    val vm = koin.get<MainViewModel>()

    ComposeViewport(document.body!!) {
        App(vm)
    }
}

