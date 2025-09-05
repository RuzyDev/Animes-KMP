package br.com.arcom.apparcom.android.ui.app.navigation

import AppAnimeIcons
import androidx.annotation.StringRes
import br.com.arcom.apparcom.android.ui.screens.home.HomeNavigation
import br.com.arcom.apparcom.android.R
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: AppAnimeIcons,
    val unselectedIcon: AppAnimeIcons,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>
) {
    HOME(
        selectedIcon = AppAnimeIcons.HOME,
        unselectedIcon = AppAnimeIcons.HOME_OUTLINED,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
        route = HomeNavigation::class
    )
}

