package com.example.registarassistncia.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListaClientesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onDetalhesClienteClick: () -> Unit,
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

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "3 Registados",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))


    val clientes = listOf(
        Triple("José", "929999999","Tipo: Particular"),
        Triple("Ana", "969999999", "Tipo: Particular"),
        Triple("Empresa XPT", "271982600", "Tipo: Empresa")
    )

    clientes.forEach { clientes ->

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF4F7)
            ),
            elevation = cardElevation(
                defaultElevation = 4.dp
            ),

            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    onDetalhesClienteClick()
                }

        )
        {
         Column(
             modifier = Modifier.padding(16.dp)
         ) {

             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(
                     imageVector = Icons.Default.Person,
                     contentDescription = null
                 )

                 Spacer(modifier = Modifier.width(8.dp))

                 Text(
                     text = clientes.first,
                     style = MaterialTheme.typography.titleLarge
                 )
             }

             Spacer(modifier = Modifier.height(8.dp))

             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(
                     imageVector = Icons.Default.Phone,
                     contentDescription = null
                 )

                 Spacer(modifier = Modifier.width(8.dp))

                 Text(
                     text = clientes.second,
                     style = MaterialTheme.typography.titleMedium
                 )
             }

             Spacer(modifier = Modifier.height(8.dp))

             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(
                     imageVector = Icons.Default.Apartment,
                     contentDescription = null
                 )

                 Spacer(modifier = Modifier.width(8.dp))

                 Text(
                     text = clientes.third,
                     style = MaterialTheme.typography.titleMedium
                 )
             }

         }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(8.dp))


    Button(
            onClick = onNovoClienteClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Novo Cliente")
        }


        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(0.65f)
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