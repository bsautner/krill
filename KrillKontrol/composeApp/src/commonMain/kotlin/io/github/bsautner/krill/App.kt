package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.pi.GpioPin
import io.github.bsautner.krill.pi.RaspberryPiHeader
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App(viewModel: KrillOperations) {
    println("loading app")
    val scope = rememberCoroutineScope()

    val pins by viewModel.pins.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.start {}
    }
    MaterialTheme {
        Box(
            modifier = Modifier.padding(45.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
            ) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(onClick = {
                        println("onButtonClick 1")
                        scope.launch {
                            viewModel.testGet()
                        }

                    }) {
                        Text("Hello World 2 ${pins.size}")
                    }
                    RaspberryPiHeader(pins)

                }

            }

        }

    }
}

@Composable @Preview
fun AppPreview() {
    App(MockViewModel())
}


class MockViewModel()  : KrillOperations {
    override val pins: StateFlow<List<GpioPin>>
        get() = TODO("Not yet implemented")

    override suspend fun testGet(): String {
        TODO("Not yet implemented")
    }

    override suspend fun getPinStatus(): List<GpioPin> {
        TODO("Not yet implemented")
    }


    override suspend fun start(callback: suspend (GpioPin) -> Unit) {
        TODO("Not yet implemented")
    }



}

