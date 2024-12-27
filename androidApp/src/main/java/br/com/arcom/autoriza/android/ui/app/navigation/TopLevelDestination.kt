package br.com.arcom.autoriza.android.ui.app.navigation

import AppArcomIcons
import androidx.annotation.StringRes
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.screens.home.HomeNavigation
import br.com.arcom.autoriza.android.ui.screens.solicitacao.solicitacoes.SolicitacoesNavigation
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: AppArcomIcons,
    val unselectedIcon: AppArcomIcons,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>
) {
    HOME(
        selectedIcon = AppArcomIcons.HOME,
        unselectedIcon = AppArcomIcons.HOME_OUTLINED,
        iconTextId = R.string.home,
        titleTextId = R.string.home,
        route = HomeNavigation::class
    )
}

