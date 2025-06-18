package io.gihub.bsautner.client

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(val message: String = "")

class TestGetUseCase(private val client: KrillClient) {
    suspend operator fun invoke() = client.testGet()
}

class MainViewModel( private val testGet: TestGetUseCase) {
    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state
    fun onButtonClick() {
        // launch in whatever scope your ViewModel uses
        println("clicked")
        scope.launch {
            println("launched")
            val result = testGet()
            _state.value = UiState(message = result)
            println(result)
        }
    }
}