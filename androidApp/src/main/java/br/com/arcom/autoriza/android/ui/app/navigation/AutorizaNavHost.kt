package br.com.arcom.autoriza.android.ui.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import br.com.arcom.autoriza.android.ui.app.AppArcomState
import br.com.arcom.autoriza.android.ui.screens.home.HomeNavigation
import br.com.arcom.autoriza.android.ui.screens.home.home
import br.com.arcom.autoriza.android.ui.screens.solicitacao.detalhessolicitacao.detalhesSolicitacao
import br.com.arcom.autoriza.android.ui.screens.solicitacao.detalhessolicitacao.navigateToDetalhesSolicitacao
import br.com.arcom.autoriza.android.ui.screens.solicitacao.solicitacoes.SolicitacoesNavigation
import br.com.arcom.autoriza.android.ui.screens.solicitacao.solicitacoes.navigateToSolicitacoes
import br.com.arcom.autoriza.android.ui.screens.solicitacao.solicitacoes.solicitacoes

@Composable
fun AppArcomNavHost(
    appState: AppArcomState,
    modifier: Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeNavigation,
        modifier =  modifier
    ) {
        home (navigateToSolicitacoes = { navController.navigateToSolicitacoes() })
        solicitacoes (
            onBackClick = navController::popBackStack,
            navigateToDetalhesSolicitacao = navController::navigateToDetalhesSolicitacao
        )
        detalhesSolicitacao(
            onBackClick = navController::popBackStack,
            navigateToHome = { appState.navigateToTopLevelDestination(TopLevelDestination.HOME) }
        )
    }
}