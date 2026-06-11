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
import androidx.compose.material3.RadioButton
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.ClienteEntity
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ClienteScreen(
    modifier: Modifier = Modifier,
    clienteId: Int? = null,
    onBackClick: () -> Unit,
    onClienteGuardado: () -> Unit
)



{
    //VARIAVEIS
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nif by remember { mutableStateOf("") }
    var morada by remember { mutableStateOf("") }
    var tipoCliente by remember { mutableStateOf("PARTICULAR") }

    LaunchedEffect(clienteId) {

        if (clienteId != null) {

            val db = DatabaseProvider.getDatabase(context)

            db.clienteDao()
                .obterPorId(clienteId)
                ?.let {

                    nome = it.nome
                    telefone = it.telefone
                    email = it.email
                    nif = it.nif
                    morada = it.morada
                    tipoCliente = it.tipoCliente
                }
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
    ) {
        Text(
            text =
                if (clienteId == null)
                    "Novo Cliente"
                else
                    "Editar Cliente",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))


        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F0FE)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),

            ) {

                // Campo Nome

                OutlinedTextField(
                    value = nome,
                    onValueChange = {nome = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Nome")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Campo Telefone

                OutlinedTextField(
                    value = telefone,
                    onValueChange = {telefone = it} ,
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Telefone")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo Email

                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Email")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo NIF

                OutlinedTextField(
                    value = nif,
                    onValueChange = {nif = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Badge,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("NIF")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Campo Morada

                OutlinedTextField(
                    value = morada,
                    onValueChange = {morada = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Apartment,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Morada")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))



                //Campo TipoCliente (com opção de escolha)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Business,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Tipo de Cliente",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = tipoCliente == "PARTICULAR",
                        onClick = { tipoCliente = "PARTICULAR" }
                    )

                    Text("Particular")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = tipoCliente == "EMPRESA",
                        onClick = { tipoCliente = "EMPRESA" }
                    )

                    Text("Empresa")
                }

            }
        }



        Spacer(modifier = Modifier.height(24.dp))

        //BOTÕES

        Button(
            onClick = {

                scope.launch {

                    val db = DatabaseProvider.getDatabase(context)

                    if (clienteId == null) {

                        val resultado =
                            db.clienteDao().inserir(
                                ClienteEntity(
                                    nome = nome,
                                    telefone = telefone,
                                    email = email,
                                    nif = nif,
                                    morada = morada,
                                    tipoCliente = tipoCliente
                                )
                            )

                        if (resultado == -1L) {

                            Toast.makeText(
                                context,
                                "Já existe um cliente com este NIF",
                                Toast.LENGTH_LONG
                            ).show()

                        } else {

                            Toast.makeText(
                                context,
                                "Cliente criado com sucesso",
                                Toast.LENGTH_SHORT
                            ).show()

                            onClienteGuardado()

                            nome = ""
                            telefone = ""
                            email = ""
                            nif = ""
                            morada = ""
                            tipoCliente = "PARTICULAR"
                        }

                    } else {

                        db.clienteDao().atualizar(
                            ClienteEntity(
                                id = clienteId,
                                nome = nome,
                                telefone = telefone,
                                email = email,
                                nif = nif,
                                morada = morada,
                                tipoCliente = tipoCliente
                            )
                        )

                        Toast.makeText(
                            context,
                            "Cliente atualizado com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()

                        onClienteGuardado()
                    }
                }
            },

            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                if (clienteId == null)
                    "Guardar Cliente"
                else
                    "Atualizar Cliente"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.ArrowBack, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Voltar")
        }
    }


}