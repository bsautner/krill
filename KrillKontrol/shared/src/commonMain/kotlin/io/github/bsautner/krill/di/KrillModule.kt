package io.github.bsautner.krill.di

import io.github.bsautner.krill.client.DefaultKrillClient
import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.client.MainViewModel
import io.github.bsautner.krill.client.getHttpClient
import io.ktor.client.*
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module


val krillModule = module {
    single<HttpClient>  { getHttpClient().client }
    single { DefaultKrillClient(get()) }
    single<KrillOperations> { MainViewModel(get()) }

}

val appModule = module {

}

fun initKoin(): Koin = startKoin {
    modules(listOf(krillModule,  appModule))
}.koin
