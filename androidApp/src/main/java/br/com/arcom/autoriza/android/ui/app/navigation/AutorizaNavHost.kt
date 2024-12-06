package br.com.arcom.autoriza.android.ui.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.arcom.autoriza.android.ui.app.AutorizaAppState
import br.com.arcom.autoriza.android.ui.perfil.PerfilNavigation
import br.com.arcom.autoriza.android.ui.perfil.perfil
import br.com.arcom.autoriza.android.ui.solicitacao.detalhessolicitacao.detalhesSolicitacao
import br.com.arcom.autoriza.android.ui.solicitacao.detalhessolicitacao.navigateToDetalhesSolicitacao
import br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes.SolicitacoesNavigation
import br.com.arcom.autoriza.android.ui.solicitacao.solicitacoes.solicitacoes
import kotlin.reflect.KFunction1

@Composable
fun AutorizaNavHost(
    appState: AutorizaAppState,
    modifier: Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = PerfilNavigation,
        modifier =  modifier
    ) {
        perfil()
        solicitacoes (navController::navigateToDetalhesSolicitacao)
        detalhesSolicitacao()
    }
}