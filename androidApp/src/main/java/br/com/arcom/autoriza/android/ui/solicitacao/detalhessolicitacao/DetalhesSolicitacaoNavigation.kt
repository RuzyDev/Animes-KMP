package br.com.arcom.autoriza.android.ui.solicitacao.detalhessolicitacao

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes.SolicitacoesNavigation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class DetalhesSolicitacaoNavigation(val id: String)

fun NavController.navigateToDetalhesSolicitacao(id: String) =
    navigate(route = DetalhesSolicitacaoNavigation(id))

fun NavGraphBuilder.detalhesSolicitacao(
    onBackClick: () -> Unit
) {
    composable<DetalhesSolicitacaoNavigation> { backStackEntry ->
        val profile: DetalhesSolicitacaoNavigation = backStackEntry.toRoute()

        DetalhesSolicitacaoRoute(profile.id, onBackClick)
    }
}