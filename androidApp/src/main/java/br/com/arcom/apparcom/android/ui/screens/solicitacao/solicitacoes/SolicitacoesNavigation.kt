package br.com.arcom.apparcom.android.ui.screens.solicitacao.solicitacoes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object SolicitacoesNavigation

fun NavController.navigateToSolicitacoes(navOptions: NavOptions? = null) =
    navigate(route = SolicitacoesNavigation, navOptions)

fun NavGraphBuilder.solicitacoes(
    onBackClick: () -> Unit,
    navigateToDetalhesSolicitacao: (String) -> Unit
){
    composable<SolicitacoesNavigation> {
        SolicitacoesRoute(
            onBackClick = onBackClick,
            navigateToDetalhesSolicitacao = navigateToDetalhesSolicitacao)
    }
}