package com.example.registarassistncia.screens

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun NovaAssistenciaScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Nova Assistência",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo Cliente
        var cliente by remember { mutableStateOf("") }

        OutlinedTextField(
            value = cliente,
            onValueChange = { cliente = it },
            label = { Text("Cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Equipamento
        var equipamento by remember { mutableStateOf("") }

        OutlinedTextField(
            value =  equipamento,
            onValueChange = { equipamento = it },
            label = {Text("Equipamento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Campo Problema
        var problema by remember { mutableStateOf("") }

        OutlinedTextField(
            value = problema,
            onValueChange = { problema = it },
            label = {Text("Problema") },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Campo Diagnóstico
        var diagnostico by remember { mutableStateOf(value = "") }

        OutlinedTextField(
            value = diagnostico,
            onValueChange = { diagnostico = it},
            label = {Text("Diagnóstico")},
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Solução
        var solucao by remember { mutableStateOf("") }

        OutlinedTextField(
            value = solucao,
            onValueChange = {solucao = it},
            label = {Text("Solução")},
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {}
        ) {
            Text("Guardar")
        }
    }
}