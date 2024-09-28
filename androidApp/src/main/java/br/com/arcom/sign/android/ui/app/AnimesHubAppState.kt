package br.com.arcom.sign.app

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.arcom.sign.android.utils.TrackDisposableJank
import br.com.arcom.sign.app.navigation.AnimesHubNavigation
import br.com.arcom.sign.app.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAnimesHubAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): AnimesHubAppState {
    NavigationTrackingSideEffect(navController)
    return remember(navController, coroutineScope, windowSizeClass) {
        AnimesHubAppState(navController, coroutineScope, windowSizeClass)
    }
}

@Stable
class AnimesHubAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    var shouldShowSettingsDialog by mutableStateOf(false)
        private set

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact ||
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    fun navigate(destination: AnimesHubNavigation, route: String? = null) {
        androidx.tracing.trace("Navigation: $destination") {
            if (destination is TopLevelDestination) {
                navController.navigate(route ?: destination.getRoute()) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            } else {
                navController.navigate(route ?: destination.getRoute())
            }
        }
    }

    fun navigateAndDestroyAll(route: String) {
        while (navController.popBackStack()){}
        navController.navigate(route)
    }

    fun onBackClickWithDestination(route: String) {
        navController.popBackStack(route, false)
    }

    fun onBackClick(currentRoute: String?) {
        if (currentRoute != null) {
            navController.popBackStack(route = currentRoute,inclusive = true)
        }
    }

    fun navigateAndDestroy(route: String) {
        navController.navigate(route) {
            if (navController.currentDestination?.route != null) {
                popUpTo(navController.currentDestination!!.route!!) {
                    inclusive = true
                }
            }
        }
    }
}

/**
 * Stores information about navigation events to be used with JankStats
 */
@Composable
private fun NavigationTrackingSideEffect(navController: NavHostController) {
    TrackDisposableJank(navController) { metricsHolder ->
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            metricsHolder.state?.putState("Navigation", destination.route.toString())
        }

        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}