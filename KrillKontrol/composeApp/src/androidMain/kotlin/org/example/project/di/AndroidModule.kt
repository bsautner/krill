package org.example.project.di

import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.client.MainViewModel
import org.example.project.MainAndroidViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {

   singleOf(::MainViewModel) { bind<KrillOperations>() }
   viewModelOf(::MainAndroidViewModel)
}