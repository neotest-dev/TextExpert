package com.neotestdev.textexpert.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TextExpertTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = UnifiedColors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
