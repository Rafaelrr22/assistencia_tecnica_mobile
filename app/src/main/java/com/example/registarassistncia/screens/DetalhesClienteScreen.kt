package com.example.registarassistncia.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetalhesClienteScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
)
{
    Column(

    ) {
        Text(
            text = "Detalhes Cliente",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically){
            Icon(Icons.Default.Person, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("José Silva")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Phone, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("969999999")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Email, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("jose@email.pt")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("NIF: 123456789")

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Home,null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Guarda")
        }

        Spacer(modifier = Modifier.height(8.dp))

        //BOTÕES

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.Edit, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Editar")
        }



        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.Delete, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Apagar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.ArrowBack, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Voltar")
        }

    }
}