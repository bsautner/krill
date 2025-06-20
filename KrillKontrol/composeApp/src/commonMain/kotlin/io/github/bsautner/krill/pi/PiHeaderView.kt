package io.github.bsautner.krill.pi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bsautner.krill.client.myJson
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun PiHeaderView(
    modifier: Modifier = Modifier,
    pins: List<GpioPin>,

) {
    Box(modifier) {
      Text(text = "PiHeaderView 3 ${pins == null}")
        RaspberryPiHeader(pins)
    }
}

@Preview
@Composable
private fun PreviewPiHeaderView() {
    val pins = myJson.decodeFromString<List<GpioPin>>(headerJson)

    PiHeaderView(modifier = Modifier.fillMaxSize(), pins = pins)
}

@Composable
fun GpioPinView(pin: GpioPin) {
   // val state = remember { mutableStateOf(pin.initialState) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .width(48.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(
                    if (!pin.isConfigurable) Color.Gray
                    else if (pin.state == "HIGH") Color.Green else Color.Red
                )
                .border(1.dp, Color.Black, CircleShape)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                fontSize = 8.sp,
                text = pin.number.toString(),
            )
        }

        Text(
            text = "[${pin.name.trim()}]",
            fontSize = 10.sp,
            maxLines = 1,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}


@Composable
fun RaspberryPiHeader(pins: List<GpioPin>) {
    Row(modifier = Modifier.padding(18.dp)) {
        Column {
            pins.filterIndexed { index, _ -> index % 2 == 0 }.forEach { pin ->
                GpioPinView(pin)
            }
        }
        Spacer(modifier = Modifier.width(18.dp))
        Column {
            pins.filterIndexed { index, _ -> index % 2 == 1 }.forEach { pin ->
                GpioPinView(pin)
            }
        }
    }
}
