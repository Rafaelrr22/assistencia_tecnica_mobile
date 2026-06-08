package com.example.registarassistncia.screens

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun NovaAssistenciaScreen(
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
            text = "Nova Assistência",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO CLIENTE
        var cliente by remember { mutableStateOf("") }

        OutlinedTextField(
            value = cliente,
            onValueChange = { cliente = it },
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text("Cliente")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO EQUIPAMENTO
        var equipamento by remember { mutableStateOf("") }

        OutlinedTextField(
            value = equipamento,
            onValueChange = { equipamento = it },
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text("Equipamento")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        //CAMPO PROBLEMA
        var problema by remember { mutableStateOf("") }

        OutlinedTextField(
            value = problema,
            onValueChange = { problema = it },
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text("Problema")
                }
            },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        //CAMPO DIAGNÓSTICO
        var diagnostico by remember { mutableStateOf(value = "") }

        OutlinedTextField(
            value = diagnostico,
            onValueChange = { diagnostico = it },
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text("Diagnóstico")
                }
            },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        //CAMOP SOLUÇÃO
        var solucao by remember { mutableStateOf("") }

        OutlinedTextField(
            value = solucao,
            onValueChange = { solucao = it },
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Default.BuildCircle,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text("solução")
                }
            },
            minLines = 4,
            modifier = Modifier.fillMaxWidth()
        )

        //CAMPO ORÇAMENTO
        var orcamento by remember { mutableStateOf("") }

        OutlinedTextField(
            value = orcamento,
            onValueChange = { orcamento = it },
            label = {
                Row {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text("Orçamento")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        //CAMPO ESTADO


        //CAMPO DATA PREVISTA


        Spacer(modifier = Modifier.height(16.dp))


        //BOTÕES
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {

            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Guardar")
        }

        Button(
            onBackClick
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Voltar")
        }
    }
}