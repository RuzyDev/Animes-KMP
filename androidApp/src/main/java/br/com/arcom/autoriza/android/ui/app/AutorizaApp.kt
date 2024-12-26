package br.com.arcom.autoriza.android.ui.app

import AppArcomIcons
import Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import br.com.arcom.autoriza.android.ui.app.navigation.AppArcomNavHost
import br.com.arcom.autoriza.android.ui.designsystem.components.AppArcomTopBarDrawer
import br.com.arcom.autoriza.designsystem.theme.divider
import br.com.arcom.autoriza.model.Usuario
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppArcomApp(
    usuario: Usuario?,
    appState: AppArcomAppState = rememberAppArcomAppState(),
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    val currentDestination = appState.currentDestination
    val currentTopLevelDestination = appState.currentTopLevelDestination
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerAppArcom(
                appState = appState,
                currentDestination = currentDestination,
                closeDrawer = { scope.launch { drawerState.close() } },
                usuario = usuario
            )
        },
        gesturesEnabled = false,
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier.semantics {
                testTagsAsResourceId = true
            },
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                AnimatedVisibility(currentTopLevelDestination != null) {
                    val title =
                        currentTopLevelDestination?.let { stringResource(it.titleTextId) } ?: ""
                    AppArcomTopBarDrawer(title = title) {
                        scope.launch { drawerState.open() }
                    }
                }
            }
        ) { padding ->
            AppArcomNavHost(
                appState = appState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }
}

@Composable
fun DrawerAppArcom(
    appState: AppArcomAppState, currentDestination: NavDestination?,
    closeDrawer: () -> Unit,
    usuario: Usuario?
) {
    val state = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxWidth(0.9f)
            .fillMaxHeight()
            .verticalScroll(state)
    ) {
        Row(
            Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .fillMaxWidth(.9f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(end = 4.dp)) {
                usuario?.let {
                    Text(
                        text = it.nome,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = it.id.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            AppArcomIcons.CLOSE.Composable(
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        onClick = closeDrawer,
                        indication = null,
                        interactionSource = null
                    ),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.divider())

        appState.topLevelDestinations.forEach { destination ->
            val selected = currentDestination.isRouteInHierarchy(destination.route)
            NavigationDrawerItem(
                label = { Text(text = stringResource(destination.iconTextId)) },
                selected = selected,
                onClick = {
                    appState.navigateToTopLevelDestination(destination)
                    closeDrawer()
                },
                icon = { destination.unselectedIcon.Composable() },
                modifier = Modifier.fillMaxWidth(.9f)
            )
        }
    }
}

private fun Modifier.notificationDot(): Modifier =
    composed {
        val tertiaryColor = MaterialTheme.colorScheme.tertiary
        drawWithContent {
            drawContent()
            drawCircle(
                tertiaryColor,
                radius = 5.dp.toPx(),
                // This is based on the dimensions of the NavigationBar's "indicator pill";
                // however, its parameters are private, so we must depend on them implicitly
                // (NavigationBarTokens.ActiveIndicatorWidth = 64.dp)
                center = center + Offset(
                    64.dp.toPx() * .45f,
                    32.dp.toPx() * -.45f - 6.dp.toPx(),
                ),
            )
        }
    }

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

