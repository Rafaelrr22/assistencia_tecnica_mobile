package com.example.registarassistncia.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EquipamentoScreen (
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
    )
    {
     Text(
         text = "Equipamento",
         style = MaterialTheme.typography.headlineMedium
     )
        Spacer(modifier = Modifier.height(8.dp))


        // Campo Marca
        var marca by remember { mutableStateOf("") }

        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it},
            label = { Text ("Marca") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo Modelo
        var modelo by remember {mutableStateOf("")}

        OutlinedTextField(
            value = modelo,
            onValueChange = { modelo = it},
            label = { Text ("Marca")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo Numero Serie
        var numSerie by remember { mutableStateOf("") }

        OutlinedTextField(
            value = numSerie,
            onValueChange = {numSerie = it},
            label = {Text("Número Série")},
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier = modifier.height(8.dp))














    }






}