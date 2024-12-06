package br.com.arcom.autoriza.android.ui.perfil

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PerfilRoute() {
Box(Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center)
    {
        Text(text = "Perfil")
    }
}

