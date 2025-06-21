package io.github.bsautner.krill.pi

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.client.Screen
import io.github.bsautner.krill.previewmocks.MockViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PiPinConfig(vm: KrillOperations) {
    Box(Modifier.fillMaxSize()) {
        vm.selectedPin.value?.let {
            ConfigScreen(vm, it)
        }
    }
}

@Composable
fun ConfigScreen(vm: KrillOperations, pin: GpioPin) {
    GpioPinConfigForm(
        pin = pin,
        onSave = { updatedPin ->
            println("Updated Pin: $updatedPin")
            // Send to server or state
        },
        onCancel = {
            vm.selectedPin.value = null
            vm.screen.value = Screen.MAIN
            println("Cancelled")
        }
    )
}



@Composable
fun GpioPinConfigForm(
    pin: GpioPin,
    onSave: (GpioPin) -> Unit,
    onCancel: () -> Unit
) {
    var selectedMode by remember { mutableStateOf(pin.mode ?: "IN") }
    var selectedState by remember { mutableStateOf(pin.state) }
    var isConfigurable by remember { mutableStateOf(pin.isConfigurable) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Configure Pin ${pin.number} (${pin.name})", fontSize = 16.sp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Mode:")
            Spacer(Modifier.width(8.dp))
            DropdownSelector(
                options = listOf("IN", "OUT"),
                selected = selectedMode,
                onSelected = { selectedMode = it }
            )
        }

        if (selectedMode == "OUT") {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("State:")
                Spacer(Modifier.width(8.dp))
                DropdownSelector(
                    options = listOf(State.LOW.name, State.HIGH.name),
                    selected = selectedState.name,
                    onSelected = { selectedState = State.valueOf(it) }
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isConfigurable,
                onCheckedChange = { isConfigurable = it }
            )
            Text("Configurable")
        }

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                onSave(
                    pin.copy(
                        mode = selectedMode,
                        state = if (selectedMode == "OUT") selectedState else State.NA,
                        isConfigurable = isConfigurable
                    )
                )
            }) {
                Text("Save")
            }

            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}


@Composable
fun DropdownSelector(
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(selected)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach {
                DropdownMenuItem(onClick = {
                    onSelected(it)
                    expanded = false
                }) {
                    Text(it)
                }
            }
        }
    }
}

@Composable @Preview
fun PiPinConfigPreview() {
    PiPinConfig(vm = MockViewModel())

}