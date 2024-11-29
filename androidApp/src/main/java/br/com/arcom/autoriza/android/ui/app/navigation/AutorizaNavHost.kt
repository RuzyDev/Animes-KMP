package br.com.arcom.autoriza.android.ui.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import br.com.arcom.autoriza.android.ui.solicitacoes.SolicitacoesNavigation
import br.com.arcom.autoriza.android.ui.solicitacoes.solicitacoes
import kotlin.reflect.KFunction1

@Composable
fun AutorizaNavHost(
    navController: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = SolicitacoesNavigation.getRoute(),
    onBackClickWithDestination: KFunction1<String, Unit>,
    navigate: (AutorizaNavigation, String?) -> Unit,
    navigateAndDestroy: (String) -> Unit,
    navigateAndDestroyAll: (String) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        //Home
        solicitacoes()
    }
}