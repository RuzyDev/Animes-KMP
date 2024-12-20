package br.com.arcom.autoriza.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import br.com.arcom.autoriza.android.ui.app.AppArcomApp
import br.com.arcom.autoriza.designsystem.theme.AppArcomTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppArcomTheme {
                AppArcomApp()
            }
        }
    }
}