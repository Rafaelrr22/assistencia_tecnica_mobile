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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.registarassistncia.data.entity.ClienteEntity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.registarassistncia.data.database.DatabaseProvider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Composable
fun ListaClientesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onDetalhesClienteClick: (Int) -> Unit,
    onNovoClienteClick: () -> Unit
) {

    //VARIÁVEIS

    val context = LocalContext.current
    val clientes = remember {
        mutableStateListOf<ClienteEntity>()
    }

    var pesquisa by remember {
        mutableStateOf("")
    }

    val clientesFiltrados = clientes.filter {

        it.nome.contains(
            pesquisa,
            ignoreCase = true
        )
    }


    LaunchedEffect(Unit) {

        val db = DatabaseProvider.getDatabase(context)

        clientes.clear()

        clientes.addAll(
            db.clienteDao().listarTodos()
        )
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "Clientes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = pesquisa,
            onValueChange = {
                pesquisa = it
            },
            label = {
                Text("Procurar cliente")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },

            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = Color.DarkGray,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = Color.DarkGray
            ),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${clientesFiltrados.size} Registados",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))



        if (clientesFiltrados.isEmpty()) {

            Text(
                text = "Nenhum cliente registado",
                style = MaterialTheme.typography.bodyLarge
            )
        }



    clientesFiltrados.forEach { cliente ->

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F0FE)
            ),
            elevation = cardElevation(
                defaultElevation = 4.dp
            ),

            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    onDetalhesClienteClick(cliente.id)
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
                     text = cliente.nome,
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
                     text = cliente.telefone,
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
                     text = cliente.tipoCliente.lowercase()
                         .replaceFirstChar { it.uppercase() },
                     style = MaterialTheme.typography.titleMedium
                 )
             }

             Row(
                 verticalAlignment = Alignment.CenterVertically
             ) {
                 Icon(
                     imageVector = Icons.Default.Badge,
                     contentDescription = null
                 )

                 Spacer(modifier = Modifier.width(8.dp))

                 Text(cliente.nif)
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