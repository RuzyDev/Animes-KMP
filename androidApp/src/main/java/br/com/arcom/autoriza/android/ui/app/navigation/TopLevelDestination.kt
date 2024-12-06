package br.com.arcom.autoriza.android.ui.app.navigation

import AutorizaIcons
import androidx.annotation.StringRes
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.perfil.PerfilNavigation
import br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes.SolicitacoesNavigation
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val selectedIcon: AutorizaIcons,
    val unselectedIcon: AutorizaIcons,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>
) {
    SOLICITACOES(
        selectedIcon = AutorizaIcons.CHECK_CIRCLE,
        unselectedIcon = AutorizaIcons.CHECK_CIRCLE_OUTLINED,
        iconTextId = R.string.solicitacoes,
        titleTextId = R.string.solicitacoes,
        route = SolicitacoesNavigation::class
    ),
    PERFIL(
        selectedIcon = AutorizaIcons.PERSON,
        unselectedIcon = AutorizaIcons.PERSON_OUTLINED,
        iconTextId = R.string.perfil,
        titleTextId = R.string.perfil,
        route = PerfilNavigation::class
    );
}

