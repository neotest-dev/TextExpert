package com.neotestdev.textexpert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.neotestdev.textexpert.ui.ExpertScreen
import com.neotestdev.textexpert.ui.theme.TextExpertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TextExpertTheme {
                ExpertScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
