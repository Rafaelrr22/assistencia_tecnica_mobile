package com.example.registarassistncia.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button

@Composable
fun ListaAssistenciasScreen(
    onBackClick: () -> Unit
) {
    Text("Lista de Assistencias")


    Button(
       onClick = onBackClick
    ){
        Text("Voltar")
    }
}
