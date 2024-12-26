package br.com.arcom.autoriza.android.ui.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import br.com.arcom.autoriza.android.ui.app.AppArcomAppState
import br.com.arcom.autoriza.android.ui.solicitacao.detalhessolicitacao.detalhesSolicitacao
import br.com.arcom.autoriza.android.ui.solicitacao.detalhessolicitacao.navigateToDetalhesSolicitacao
import br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes.SolicitacoesNavigation
import br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes.solicitacoes

@Composable
fun AppArcomNavHost(
    appState: AppArcomAppState,
    modifier: Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = SolicitacoesNavigation,
        modifier =  modifier
    ) {
        solicitacoes (navController::navigateToDetalhesSolicitacao)
        detalhesSolicitacao(
            onBackClick = navController::popBackStack,
            navigateToSolicitacoes = { appState.navigateToTopLevelDestination(TopLevelDestination.SOLICITACOES) }
        )
    }
}