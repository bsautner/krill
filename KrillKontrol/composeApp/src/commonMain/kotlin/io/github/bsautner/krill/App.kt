package org.example.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.gihub.bsautner.krill.client.KrillViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: KrillViewModel) {

    MaterialTheme {
        Box(
            modifier = Modifier.padding(45.dp),
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    println("onButtonClick 1")
                    viewModel::onButtonClick.invoke()
                }) {
                    Text("Hello World")
                }

            }
        }

    }
}


@Composable
@Preview
fun AppIos() {

    MaterialTheme {
        Box(
            modifier = Modifier.padding(45.dp),
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    println("ios onButtonClick 1")

                }) {
                    Text("Hello iOS")
                }

            }
        }

    }
}