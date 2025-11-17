package com.neotestdev.textexpert.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neotestdev.textexpert.engine.InferenceEngine
import com.neotestdev.textexpert.engine.expertRules
import kotlinx.coroutines.launch


@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExpertScreen(modifier: Modifier = Modifier) {

    var inputText by remember { mutableStateOf("") }
    var results by remember { mutableStateOf(listOf<String>()) }
    var showAlert by remember { mutableStateOf(false) }

    val clipboard = LocalClipboardManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Text Expert",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    "Tu sistema experto para analizar textos, detectar errores y darte recomendaciones.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                IconButton(onClick = {
                    clipboard.getText()?.let { inputText = it.text }
                }) {
                    Icon(Icons.Default.ContentPaste, contentDescription = "Pegar texto")
                }

                IconButton(onClick = {
                    inputText = ""
                    results = emptyList()
                }) {
                    Icon(Icons.Default.Delete, contentDescription = "Limpiar texto")
                }
            }

            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Escribe o pega tu texto") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                maxLines = 8
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (inputText.trim().isEmpty()) {
                        showAlert = true
                    } else {
                        val engine = InferenceEngine(expertRules)
                        results = engine.infer(inputText)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Analizar Texto")
            }

            Spacer(modifier = Modifier.height(20.dp))


            if (results.isNotEmpty()) {
                OutlinedButton(
                    onClick = {
                        val fullText = results.joinToString("\n• ")
                        clipboard.setText(AnnotatedString(fullText))

                        scope.launch {
                            snackbarHostState.showSnackbar("Análisis copiado al portapapeles")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Copiar análisis")
                }

                Spacer(modifier = Modifier.height(12.dp))
            }


            Text(
                text = "Resultados del análisis:",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))


            if (results.isEmpty()) {
                Text(
                    text = "No hay texto para analizar.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }


            /** --- RESULTADOS CON ANIMACIÓN --- */
            results.forEachIndexed { index, rec ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 })
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(
                            text = rec,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }

    /** --- ALERTA SI NO INGRESA TEXTO --- */
    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("Aviso") },
            text = { Text("Debes ingresar un texto para analizar.") },
            confirmButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text("Entendido")
                }
            }
        )
    }
}

