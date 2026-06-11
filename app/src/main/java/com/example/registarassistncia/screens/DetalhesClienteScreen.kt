package com.example.registarassistncia.screens

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.material3.AlertDialog

import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.ClienteEntity

@Composable
fun DetalhesClienteScreen(
    modifier: Modifier = Modifier,
    clienteId: Int,
    onEditarClick: (Int) -> Unit,
    onBackClick: () -> Unit
)




{

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    var mostrarDialog by remember {
        mutableStateOf(false)
    }


    var cliente by remember {
        mutableStateOf<ClienteEntity?>(null)
    }

    LaunchedEffect(clienteId) {

        val db = DatabaseProvider.getDatabase(context)

        cliente =
            db.clienteDao().obterPorId(clienteId)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)

    ) {
        Text(
            text = "Detalhes Cliente",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F0FE)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(Icons.Default.Person, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Nome")
                }
                Text(cliente?.nome ?: "")

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Phone, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Nº telémovel")
                }
                Text(cliente?.telefone ?: "")

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Email, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Email")
                }
                Text(cliente?.email ?: "")


                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Badge,null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("NIF")
                }
                Text(cliente?.nif ?: "")

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Home,null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Morada")
                }
                Text(cliente?.morada ?: "")
            }
        }



        Spacer(modifier = Modifier.height(8.dp))


            // BOTÃO EDITAR
        Button(
            onClick = {
                cliente?.let {
                    onEditarClick(it.id)
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.Edit, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Editar")
        }

        Spacer(modifier = Modifier.height(8.dp))

            // BOTÃO APAGAR
        Button(
            onClick = {
                mostrarDialog = true
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.Delete, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Apagar")
        }

        Spacer(modifier = Modifier.height(8.dp))

            // BOTÃO VOLTAR
        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.ArrowBack, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Voltar")
        }
    }

        //CONFIRMAÇÃO AO APAGAR

    if (mostrarDialog) {

        AlertDialog(
            onDismissRequest = {
                mostrarDialog = false
        },
            title = {
                Text("Apagar Cliente")
        },
            text = {
                Text("Tem a certeza que pretende apagar este cliente?")
        },
            confirmButton = {

                Button(
                    onClick = {

                        mostrarDialog = false

                        scope.launch {

                         cliente?.let {

                            val db = DatabaseProvider.getDatabase(context)

                            db.clienteDao().apagar(it)

                            onBackClick()
                        }
                    }
                }
            )
             {
                Text("Apagar")
             }
        },
        dismissButton = {

            Button(
                onClick = {
                    mostrarDialog = false
                 }
                )
                {
                    Text("Cancelar")
                }
            }
        )
    }
}
