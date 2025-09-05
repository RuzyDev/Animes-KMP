package br.com.arcom.apparcom.android.ui.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import br.com.arcom.apparcom.android.ui.app.navigation.AppAnimeNavHost

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppAnimeApp(
    appState: AppAnimeState = rememberAppAnimeState()
) {
   AppAnimeNavHost(
       appState = appState,
       modifier = Modifier
           .fillMaxSize()
   )
}