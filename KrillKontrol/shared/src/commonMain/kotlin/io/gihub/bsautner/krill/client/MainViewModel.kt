package io.gihub.bsautner.krill.client

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class UiState(val message: String = "")
private val viewModelJob = SupervisorJob()
private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

class TestGetUseCase(private val client: KrillClient) {
    suspend operator fun invoke() = client.testGet()
}

class MainViewModel( private val testGet: TestGetUseCase) : KoinComponent  {


    fun onButtonClick() {
        scope.launch {
            println("launched")
            val result = testGet()
            println(result)
        }
    }
}