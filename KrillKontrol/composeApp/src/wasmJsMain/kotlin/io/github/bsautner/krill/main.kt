package org.example.project

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.di.initKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val koin = initKoin()
    val vm = koin.get<KrillOperations>()

    ComposeViewport(document.body!!) {
        App(vm)
    }
}

