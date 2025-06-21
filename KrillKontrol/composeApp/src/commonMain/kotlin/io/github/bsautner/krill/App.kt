package org.example.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.client.Screen
import io.github.bsautner.krill.pi.PiPinConfig
import io.github.bsautner.krill.pi.RaspberryPiHeader
import io.github.bsautner.krill.previewmocks.MockViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun App(vm: KrillOperations) {
    val pins by vm.pins.collectAsState()
    val screen by vm.screen.collectAsState()


    LaunchedEffect(Unit) {
        vm.start {

        }
    }

    MaterialTheme {
        Box(
            modifier = Modifier.padding(25.dp).fillMaxWidth().width(300.dp),
        ) {

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                when (screen) {

                    Screen.MAIN ->  {
                        RaspberryPiHeader(vm, pins)
                    }
                    Screen.CONFIGURE_PIN -> {
                        PiPinConfig(vm)
                    }
                }


            }

        }

    }
}

@Composable @Preview
fun AppPreview() {
    App(MockViewModel())
}




