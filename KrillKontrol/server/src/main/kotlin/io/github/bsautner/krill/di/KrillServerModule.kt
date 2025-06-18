package io.github.bsautner.krill.di

import io.github.bsautner.krill.pi.PiContextProvider
import io.github.bsautner.krill.pi.PiManager
import org.koin.dsl.module

val krillServerModule = module {
    single { PiContextProvider() }
    single { PiManager(get()) }
}