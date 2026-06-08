package com.example.registarassistncia.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.registarassistncia.model.Equipamento

@Composable
fun ListaAssistenciasScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onDetalhesClick: () -> Unit
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
        listOf("José",  "Portátil HP", "Estado: Em Diagnóstico", "08/06/2026"),
        listOf("Ana", "Desktop", "Estado: Concluída","05/06/2026"),
        listOf("Empresa Silva", "Impressora HP", "Estado: Aguardar Peças","08/06/2026")
    )

    assistencias.forEach { assistencias ->

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onDetalhesClick()
                }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(assistencias[0])
                Text(assistencias[1])
                Text(assistencias[2])
                Text(assistencias[3])
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(8.dp))


     Button(
            onClick = onDetalhesClick
        )
     {
        Text("Ver detalhes")
     }

    Button(
        onClick = onBackClick
    ) {
        Text("Voltar")
        }

    }
}
