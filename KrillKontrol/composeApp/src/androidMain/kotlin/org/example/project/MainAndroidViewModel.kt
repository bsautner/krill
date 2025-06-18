package org.example.project

import androidx.lifecycle.ViewModel
import io.gihub.bsautner.krill.client.KrillViewModel
import io.gihub.bsautner.krill.client.MainViewModel

class MainAndroidViewModel(private val mainViewModel: MainViewModel):  ViewModel(), KrillViewModel {

    override fun onButtonClick() {
        mainViewModel.onButtonClick()
    }


}