package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       val vm: MainAndroidViewModel by viewModel()
        setContent {

            ViewModelInject(vm)
        }
    }
}

@Composable
fun ViewModelInject(vm: MainAndroidViewModel = koinViewModel()){
    App(vm)
}