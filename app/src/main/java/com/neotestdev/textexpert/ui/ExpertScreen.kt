package com.neotestdev.textexpert.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotestdev.textexpert.engine.InferenceEngine
import com.neotestdev.textexpert.engine.expertRules

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpertScreen(modifier: Modifier = Modifier) {

    var inputText by remember { mutableStateOf("") }
    var results by remember { mutableStateOf(listOf<String>()) }

    Column(modifier = modifier.padding(16.dp)) {

        Text("Sistema Experto – Procesador de Texto", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Escribe o pega tu texto") },
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val engine = InferenceEngine(expertRules)
                results = engine.infer(inputText)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Analizar Texto")
        }

        Spacer(modifier = Modifier.height(20.dp))

        results.forEach { rec ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = rec,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}