package com.example.registarassistncia.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ListaEquipamentoScreen(
    modifier: Modifier = Modifier,
    onNovoEquipamentoClick: () -> Unit,
    onBackClick: () -> Unit
)
{


    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    )
    {
        Text(
            text = "Equipamento",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        val equipamento = listOf(
            "Portátil Asus",
            "Desktop HP",
            "Impressora HP"
        )
        equipamento.forEach {
            Text(it)
            Spacer(modifier= Modifier.height(8.dp))
        }

        Button(
            onClick = onNovoEquipamentoClick
        ) {
            Text("Novo")
        }

        Button(
            onClick = onBackClick
        ) {
            Text("Voltar")
        }
    }

}