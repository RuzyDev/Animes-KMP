package br.com.arcom.apparcom.android.ui.screens.login

import AppAnimeIcons
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.apparcom.android.ui.designsystem.components.AppAnimeScaffold
import br.com.arcom.apparcom.presentation.LoginViewModel
import br.com.arcom.apparcom.ui.designsystem.components.text.AppAnimeTextField
import org.koin.compose.koinInject
import br.com.arcom.apparcom.android.ui.designsystem.components.AppAnimeButton
import br.com.arcom.apparcom.presentation.LoginUiState
import br.com.arcom.apparcom.android.R
import br.com.arcom.apparcom.ui.designsystem.components.text.AppAnimeKeyboardActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LoginScreen(
        clearMessage = viewModel::clearMessage,
        realizarLogin = viewModel::realizarLogin,
        uiState = uiState
    )
}

@Composable
fun LoginScreen(
    clearMessage: (Long) -> Unit,
    realizarLogin: (Long, String) -> Unit,
    uiState: LoginUiState
) {
    var idUsuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var ocultarSenha by remember { mutableStateOf(true) }
    val keyboard = LocalSoftwareKeyboardController.current


    AppAnimeScaffold (
        clearMessage = clearMessage,
        uiMessage = uiState.uiMessage,
        loading = uiState.loadingLogin
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
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.entre_com_dados_continuar),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(0.9f),
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = "Logo app",
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .weight(1f)
                    .padding(vertical = 16.dp)
            )

            AppAnimeTextField(
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

            AppAnimeTextField(
                text = senha,
                setText = { senha = it },
                label = stringResource(id = R.string.senha),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = AppAnimeKeyboardActions(onNext = {
                    keyboard?.hide()
                    idUsuario.toLongOrNull()?.let { realizarLogin(it, senha) }
                }),
                singleLine = true,
                icon = if (ocultarSenha) AppAnimeIcons.NAO_VISIVEL else AppAnimeIcons.VISIVEL,
                iconClick = { ocultarSenha = !ocultarSenha },
                visualTransformation = if (ocultarSenha) PasswordVisualTransformation() else VisualTransformation.None,
            )

            AppAnimeButton(
                onClick = {
                    idUsuario.toLongOrNull()?.let { realizarLogin(it, senha) }
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