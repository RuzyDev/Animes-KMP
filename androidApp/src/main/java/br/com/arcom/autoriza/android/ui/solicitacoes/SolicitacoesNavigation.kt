package br.com.arcom.autoriza.android.ui.solicitacoes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.arcom.autoriza.android.ui.app.navigation.AutorizaNavigation

object SolicitacoesNavigation: AutorizaNavigation("home")

fun NavGraphBuilder.solicitacoes(){
    composable(
        route = SolicitacoesNavigation.getRoute()
    ) {
        SolicitacoesRoute()
    }
}