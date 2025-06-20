package io.github.bsautner.krill.di

import io.github.bsautner.krill.client.DefaultKrillClient
import io.github.bsautner.krill.client.KrillClient
import io.github.bsautner.krill.client.KrillViewModel
import io.github.bsautner.krill.client.MainViewModel
import io.github.bsautner.krill.client.KrillApiClient
import io.github.bsautner.krill.client.getHttpClient
import io.ktor.client.*
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module


val krillModule = module {
    single<HttpClient>  { getHttpClient().client }
    single<KrillClient> { DefaultKrillClient(get()) }
    single { KrillApiClient(get()) }
    single<KrillViewModel> { MainViewModel(get()) }

}

val appModule = module {

}

fun initKoin(): Koin = startKoin {
    modules(listOf(krillModule,  appModule))
}.koin
