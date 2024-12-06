package br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object SolicitacoesNavigation

fun NavController.navigateToSolicitacoes(navOptions: NavOptions) =
    navigate(route = SolicitacoesNavigation, navOptions)

fun NavGraphBuilder.solicitacoes(
    navigateToDetalhesSolicitacao: (String) -> Unit
){
    composable<SolicitacoesNavigation> {
        SolicitacoesRoute(navigateToDetalhesSolicitacao = navigateToDetalhesSolicitacao)
    }
}