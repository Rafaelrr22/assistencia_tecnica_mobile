package com.example.registarassistncia.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListaAssistenciasScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {



    Text(
        text = "Assistencias",
        style = MaterialTheme.typography.headlineMedium
    )

    Spacer(modifier = Modifier.height(8.dp))

    val assistencias = listOf(
        "José - Portátil HP - EM_DIAGNOSTICO",
        "Ana - Desktop - CONCLUIDA",
        "Empresa Silva - Impressora HP - AGUARDAR_PECAS"
    )

    assistencias.forEach {
        Text(it)
        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(8.dp))


    Button(
        onClick = onBackClick
    ) {
        Text("Voltar")
    }
}
}
