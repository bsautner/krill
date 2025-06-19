package io.github.bsautner.krill

import androidx.compose.ui.window.ComposeUIViewController
import io.github.bsautner.krill.client.KrillViewModel
import io.github.bsautner.krill.di.initKoin
import org.example.project.App

val koin = initKoin()
val vm: KrillViewModel = koin.get()
fun MainViewController() = ComposeUIViewController { App(vm) }


