package com.neotestdev.textexpert.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val BluePrimary = Color(0xFF3B82F6)
val BlueSecondary = Color(0xFF60A5FA)
val BlueTertiary = Color(0xFF93C5FD)

val DarkBackground = Color(0xFF0D1117)
val DarkSurface = Color(0xFF161B22)

// Light Mode
val LightColors = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    tertiary = BlueTertiary,
)

// Dark Mode
val DarkColors = darkColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    tertiary = BlueTertiary,
    background = DarkBackground,
    surface = DarkSurface
)
