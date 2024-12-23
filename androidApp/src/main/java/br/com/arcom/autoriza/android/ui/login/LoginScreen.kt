package br.com.arcom.autoriza.android.ui.login

import AppArcomIcons
import Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.android.ui.designsystem.components.AppArcomScaffold
import br.com.arcom.autoriza.presentation.LoginViewModel
import br.com.arcom.autoriza.presentation.util.UiMessage
import br.com.arcom.autoriza.ui.designsystem.components.text.AppArcomTextField
import org.koin.compose.koinInject
import br.com.arcom.autoriza.android.R
import br.com.arcom.autoriza.android.ui.designsystem.components.AppArcomButton
import br.com.arcom.autoriza.ui.designsystem.components.text.rememberFieldString

@Composable
fun LoginRoute(
    onBackClick: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = koinInject(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        onBackClick = onBackClick,
        clearMessage = viewModel::clearMessage,
        realizarLogin = viewModel::realizarLogin,
        navigateToHome = navigateToHome,
        uiState = uiState
    )
}

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    clearMessage: (Long) -> Unit,
    realizarLogin: (Long, String, () -> Unit) -> Unit,
    navigateToHome: () -> Unit,
    uiState: UiMessage? = null
) {
    var idUsuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var ocultarSenha by remember { mutableStateOf(true) }


    AppArcomScaffold (
        onBackClick = onBackClick,
        clearMessage = clearMessage,
        uiMessage = uiState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.bem_vindo),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth(0.9f),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.entre_com_dados_continuar),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(0.9f),
                fontWeight = FontWeight.Normal
            )

            AppArcomIcons.ALERTA.Composable(
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(200.dp)
                    .padding(vertical = 16.dp)
            )

            AppArcomTextField(
                text = idUsuario,
                setText = { idUsuario = it },
                label = stringResource(id = R.string.usuario),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                maxLines = 6,
                singleLine = true
            )

            AppArcomTextField(
                text = senha,
                setText = { senha = it },
                label = stringResource(id = R.string.senha),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                maxLength = 8,
                singleLine = true,
                icon = if (ocultarSenha) AppArcomIcons.NAO_VISIVEL else AppArcomIcons.VISIVEL,
                iconClick = { ocultarSenha = !ocultarSenha }
            )

            AppArcomButton(
                onClick = {
                    realizarLogin(idUsuario.toLong(), senha, navigateToHome)
                },
                text = stringResource(id = R.string.entrar),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                enabled = idUsuario.toLongOrNull() != null && senha.isNotEmpty()
            )
        }
    }
}