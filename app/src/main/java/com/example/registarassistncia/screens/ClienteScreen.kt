package com.example.registarassistncia.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

@Composable
fun ClienteScreen(
    modifier: Modifier = Modifier
    )





{
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Cliente",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo Nome
        var nome by remember { mutableStateOf(value = "") }

        OutlinedTextField(
            value = nome,
            onValueChange = {nome = it},
            label = { Text("Nome")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo Telefone
        var telefone by remember   {mutableStateOf(value = "")}

        OutlinedTextField(
            value = telefone,
            onValueChange = {telefone = it} ,
            label = { Text("Telefone")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Email
        var email by remember { mutableStateOf(value = "") }

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo NIF
        var nif by remember { mutableStateOf(value = "") }

        OutlinedTextField(
            value = nif,
            onValueChange = {nif = it},
            label = { Text("NIF")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Morada
        var morada by remember { mutableStateOf("") }

        OutlinedTextField(
            value = morada,
            onValueChange = {morada = it},
            label = { Text("Morada")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        //Campo TipoCliente



        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Guardar")
        }

    }


}