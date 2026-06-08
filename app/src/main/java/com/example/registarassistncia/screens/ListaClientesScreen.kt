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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListaClientesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNovoClienteClick: () -> Unit
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
            text = "Clientes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))


    val clientes = listOf(
        Triple("José", "929999999","Tipo: Particular"),
        Triple("Ana", "969999999", "Tipo: Particular"),
        Triple("Empresa XPT", "271982600", "Tipo: Empresa")
    )

    clientes.forEach { clientes ->

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{

                }

        )
        {
         Column(
             modifier = Modifier.padding(16.dp)
         ) {
             Text(clientes.first)
             Text(clientes.second)
             Text(clientes.third)
         }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = onNovoClienteClick
    ) {
        Text("Criar")
    }


    Button(
        onClick = onBackClick
    ) {
        Text("Voltar")
    }
}
}