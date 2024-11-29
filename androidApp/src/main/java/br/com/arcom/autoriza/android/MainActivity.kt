package br.com.arcom.autoriza.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import br.com.arcom.autoriza.app.AutorizaApp
import br.com.arcom.autoriza.designsystem.theme.AutorizaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)

            AutorizaTheme {
                AutorizaApp(windowSizeClass = windowSizeClass)
            }
        }
    }
}