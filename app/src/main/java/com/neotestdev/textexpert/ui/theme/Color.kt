package com.neotestdev.textexpert.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val UnifiedColors = darkColorScheme(
    primary = Color(0xFF1877F2),        // Azul fuerte para botones
    onPrimary = Color(0xFFFFFFFF),      // Texto blanco en botones

    background = Color(0xFF000000),     // Fondo negro REAL
    onBackground = Color(0xFFEAEAEA),   // Texto claro sobre negro

    surface = Color(0xFF0D0D0D),        // Superficie casi negra
    onSurface = Color(0xFFEAEAEA),      // Texto en cards, topbars, etc.

    surfaceVariant = Color(0xFF1A1A1A), // Campos de texto, cajas
    onSurfaceVariant = Color(0xFFEAEAEA),

    outline = Color(0xFF1877F2)         // Bordes azulados si se necesitan
)

