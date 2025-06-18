package io.gihub.bsautner.krill.di

import io.gihub.bsautner.krill.client.*
import io.ktor.client.*
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module


val krillModule = module {
    single<HttpClient>  { getHttpClient().client }
    single<KrillClient> { DefaultKrillClient(get()) }
    single { TestGetUseCase(get()) }
    single { MainViewModel(get()) }

  //  viewModelOf(::MainViewModel)
}

fun initKoin(): Koin = startKoin {
    modules(krillModule)
}.koin
