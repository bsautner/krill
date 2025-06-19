package org.example.project

import androidx.lifecycle.ViewModel
import io.github.bsautner.krill.client.KrillViewModel
import io.github.bsautner.krill.client.MainViewModel

class MainAndroidViewModel(private val mainViewModel: MainViewModel):  ViewModel(), KrillViewModel {

    override fun onButtonClick() {
        mainViewModel.onButtonClick()
    }


}