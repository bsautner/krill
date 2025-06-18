package org.example.project

import android.app.Application
import io.gihub.bsautner.krill.di.krillModule
import org.example.project.di.androidModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KrillApplication : Application() {
  override fun onCreate() {
    super.onCreate()
  //  initKoin()
    startKoin {

      androidContext(this@KrillApplication)

      modules(listOf(krillModule, androidModule))

    }
  }
}