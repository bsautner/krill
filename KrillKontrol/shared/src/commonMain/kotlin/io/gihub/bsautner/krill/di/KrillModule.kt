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
    single<KrillViewModel> { MainViewModel(get()) }

}

val appModule = module {

}

fun initKoin(): Koin = startKoin {
    modules(listOf(krillModule,  appModule))
}.koin
