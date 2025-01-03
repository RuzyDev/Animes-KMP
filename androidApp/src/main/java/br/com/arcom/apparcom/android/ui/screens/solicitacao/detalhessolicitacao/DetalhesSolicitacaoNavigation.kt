package br.com.arcom.apparcom.android.ui.screens.solicitacao.detalhessolicitacao

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class DetalhesSolicitacaoNavigation(val id: String)

fun NavController.navigateToDetalhesSolicitacao(id: String) =
    navigate(route = DetalhesSolicitacaoNavigation(id))

fun NavGraphBuilder.detalhesSolicitacao(
    onBackClick: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable<DetalhesSolicitacaoNavigation> { backStackEntry ->
        val profile: DetalhesSolicitacaoNavigation = backStackEntry.toRoute()

        DetalhesSolicitacaoRoute(profile.id, onBackClick)
    }
}