package br.com.arcom.apparcom.android.ui.screens.animedetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.arcom.apparcom.android.ui.screens.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object AnimeDetailsNavigation

fun NavController.navigateToAnimeDetails(navOptions: NavOptions) =
    navigate(route = AnimeDetailsNavigation, navOptions)

fun NavGraphBuilder.animeDetails(
) {
    composable<AnimeDetailsNavigation> {
        AnimeDetailsRoute()
    }
}