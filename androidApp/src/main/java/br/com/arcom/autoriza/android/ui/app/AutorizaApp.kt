package br.com.arcom.autoriza.app

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import br.com.arcom.autoriza.android.ui.app.navigation.AutorizaNavHost

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AutorizaApp(
    windowSizeClass: WindowSizeClass,
    appState: AutorizaAppState = rememberAutorizaAppState(
        windowSizeClass = windowSizeClass
    )
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val currentDestination = appState.currentDestination

    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        AutorizaNavHost(
            navController = appState.navController,
            onBackClick = { appState.onBackClick(currentRoute = currentDestination?.route) },
            onBackClickWithDestination = appState::onBackClickWithDestination,
            navigate = appState::navigate,
            modifier = Modifier.padding(padding),
            navigateAndDestroy = appState::navigateAndDestroy,
            navigateAndDestroyAll = appState::navigateAndDestroyAll
        )
    }
}
