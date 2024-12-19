package br.com.arcom.autoriza.android.ui.login

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.arcom.autoriza.presentation.LoginViewModel
import br.com.arcom.sig.core.util.toCPF
import br.com.arcom.sig.ui.designsystem.components.ModalBottomSheetSig
import br.com.arcom.sig.ui.designsystem.components.SigButton
import br.com.arcom.sig.ui.designsystem.components.SigScaffold
import br.com.arcom.sig.ui.designsystem.components.TextButtonSig
import br.com.arcom.sig.ui.designsystem.components.text.SigTextField
import br.com.arcom.sig.ui.designsystem.components.text.rememberFieldString
import br.com.arcom.sig.ui.designsystem.icon.Composable
import br.com.arcom.sig.ui.designsystem.icon.SigIcon
import br.com.arcom.sig.ui.designsystem.util.MaskVisualTransformation
import org.koin.compose.koinInject


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
        login = viewModel::login,
        navigateToHome = navigateToHome,
        uiState = uiState
    )
}

@Composable
fun LoginScreen(
    onBackClick: () -> Unit,
    clearMessage: (Long) -> Unit,
    login: (Long, String, () -> Unit) -> Unit,
    navigateToHome: () -> Unit,
    uiState: LoginUiState = LoginUiState.Empty
) {
    val dataNascimento = rememberFieldString()
    var ajuda by remember { mutableStateOf(false) }

    if (ajuda) {
        PrecisoAjudaContent { ajuda = false }
    }

    SigScaffold(
        onBackClick = onBackClick,
        clearMessage = clearMessage,
        uiState = uiState.uiState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(id = R.string.bem_vindo_sig),
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

            SigIcon.LOGO.Composable(
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(200.dp)
                    .padding(vertical = 16.dp)
            )

            SigTextField(
                text = uiState.dadosLogin?.cpf?.toCPF() ?: "",
                setText = {},
                label = stringResource(id = R.string.cpf),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                enabled = false,
                visualTransformation = MaskVisualTransformation("###.###.###-##"),
                maxLines = 11,
                singleLine = true
            )

            SigTextField(
                valueState = dataNascimento,
                label = stringResource(id = R.string.data_nascimento),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 8.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = MaskVisualTransformation("##/##/####"),
                maxLength = 8,
                singleLine = true
            )

            SigButton(
                onClick = {
                    uiState.dadosLogin?.cpf?.let {
                        login(it, dataNascimento.value, navigateToHome)
                    }
                },
                text = stringResource(id = R.string.entrar),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 16.dp),
                enabled = uiState.dadosLogin != null
            )

            stringResource(id = R.string.preciso_ajuda).TextButtonSig(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 4.dp)
            ) {
                ajuda = true
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PrecisoAjudaContent(close: () -> Unit) {
    ModalBottomSheetSig(onDismissRequest = close) {
        Text(
            text = stringResource(id = R.string.entre_contato_conosco),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 16.dp),
            maxLines = 2
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                SigIcon.WHATSAPP.Composable(
                    modifier = Modifier.requiredSize(36.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.numero_atendimento_suporte),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                SigIcon.TELEFONE.Composable(
                    modifier = Modifier.requiredSize(36.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(id = R.string.numero_atendimento_suporte),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}