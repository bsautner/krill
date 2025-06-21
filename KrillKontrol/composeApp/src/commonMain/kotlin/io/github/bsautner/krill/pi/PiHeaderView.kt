package io.github.bsautner.krill.pi

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bsautner.krill.client.KrillOperations
import io.github.bsautner.krill.client.Screen
import io.github.bsautner.krill.client.myJson
import io.github.bsautner.krill.previewmocks.MockViewModel
import io.github.bsautner.krill.previewmocks.MockViewModel.Companion.headerJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun PiHeaderView(
    vm: KrillOperations,
    modifier: Modifier = Modifier,
    pins: List<GpioPin>
) {
    Box(modifier) {

        RaspberryPiHeader(vm, pins)
    }
}

@Composable
fun PinView(vm: KrillOperations, pin: GpioPin) {

    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .padding(2.dp)
            .width(100.dp)
            .height(IntrinsicSize.Min)


    ) {
        if (pin.number % 2 == 0) {
            PinStatusDot (vm, scope, pin)
            Spacer(Modifier.width(4.dp))
        }

        PinMenuView(pin, modifier = Modifier.align(Alignment.CenterVertically).clickable(enabled = pin.isConfigurable , onClick = {
            vm.selectedPin.value = pin
            vm.screen.value = Screen.CONFIGURE_PIN

        }))


        if (pin.number % 2 == 1) {
            Spacer(Modifier.width(4.dp))
            PinStatusDot (vm, scope, pin)
        }
    }
}

@Composable
fun PinMenuView(pin: GpioPin, modifier: Modifier = Modifier) {

        val text = StringBuilder()
        text.append(pin.name)

        Text(
            text = text.toString(),
            fontSize = 10.sp,
            maxLines = 1,
            modifier = modifier.width(40.dp)

        )


}

@Composable
fun PinStatusDot(vm: KrillOperations, scope: CoroutineScope, pin: GpioPin) {

    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(
                if (!pin.isConfigurable) Color.Gray
                else if (pin.state == State.HIGH) Color.Green else Color.Blue
            )
            .border(1.dp, Color.Black, CircleShape)
            .clickable(enabled = true, onClick = {
                scope.launch {
                    pin.toggleState()
                    vm.updatePin(pin)

                }
            })
    ) {

            Text(
                color = Color.White,
                modifier = Modifier.align(Alignment.Center) ,
                fontSize = 12.sp,
                text = pin.number.toString(),
            )

    }
}



@Composable
fun RaspberryPiHeader(vm: KrillOperations, pins: List<GpioPin>) {

    Row(modifier = Modifier.padding(8.dp)) {
        Column {
            pins.filterIndexed { index, _ -> index % 2 == 0 }.forEach { pin ->
                PinView(vm, pin)
            }
        }
        Spacer(modifier = Modifier.width(2.dp))
        Column {
            pins.filterIndexed { index, _ -> index % 2 == 1 }.forEach { pin ->
                PinView(vm, pin)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewPiHeaderView() {
    val pins = myJson.decodeFromString<List<GpioPin>>(headerJson)
    val vm = MockViewModel()
    PiHeaderView(modifier = Modifier.fillMaxSize(), vm = vm, pins =  pins)
}


@Preview
@Composable
private fun PreviewPin1() {
    val pin  = myJson.decodeFromString<List<GpioPin>>(headerJson).firstOrNull {  it.number == 23 }
    val vm = MockViewModel()
    PinView(vm = vm, pin =  pin!!)
}

@Preview
@Composable
private fun PreviewPin2() {
    val pin  = myJson.decodeFromString<List<GpioPin>>(headerJson).firstOrNull {  it.number == 27 }
    val vm = MockViewModel()
    PinView(vm = vm, pin =  pin!!)
}

@Preview
@Composable
private fun PreviewPin3() {
    val pin  = myJson.decodeFromString<List<GpioPin>>(headerJson).firstOrNull {  it.number == 1 }
    val vm = MockViewModel()
    PinView(vm = vm, pin =  pin!!)
}