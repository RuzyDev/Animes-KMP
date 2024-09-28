package br.com.arcom.sign.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import br.com.arcom.sign.app.AnimesHubApp
import br.com.arcom.sign.designsystem.theme.AnimesHubTheme
import br.com.arcom.sign.presentation.HomeViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val windowSizeClass = calculateWindowSizeClass(activity = this)

            AnimesHubTheme {
                AnimesHubApp(windowSizeClass = windowSizeClass)
            }
        }
    }
}