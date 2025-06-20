package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.pi.GpioPin
import io.github.bsautner.krill.pi.RaspberryPiHeader
import kotlinx.coroutines.flow.StateFlow
import org.jetbrains.compose.ui.tooling.preview.Preview



@Composable
fun App(vm: KrillOperations) {
    println("loading app")
    val scope = rememberCoroutineScope()

    val pins by vm.pins.collectAsState()

    LaunchedEffect(Unit) {
        vm.start {}
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
                       RaspberryPiHeader(vm, pins)
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

