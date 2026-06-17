package com.example.registarassistncia.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.registarassistncia.ui.theme.RegistarAssistênciaTheme
import com.example.registarassistncia.utils.criarBackup
import androidx.compose.foundation.Image
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.FileProvider
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.net.Uri
import com.example.registarassistncia.utils.restaurarBackup
import com.example.registarassistncia.R
import com.example.registarassistncia.data.database.DatabaseProvider

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               onNovaAssistenciaClick: () -> Unit,
               onAssistenciasClick: () -> Unit,
               onClientesClick: () -> Unit,
               onEquipamentosClick: () -> Unit
              )
{

    //VARIÁVEIS

    val context = LocalContext.current

    var totalClientes by remember {
        mutableStateOf(0)
    }

    var totalEquipamentos by remember {
        mutableStateOf(0)
    }

    var totalAssistencias by remember {
        mutableStateOf(0)
    }

    var assistenciasPendentes by remember {
        mutableStateOf(0)
    }

    var assistenciasConcluidas by remember {
        mutableStateOf(0)
    }

    val launcherRestaurar = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->

        if (uri != null) {

            restaurarBackup(
                context,
                uri
            )

            Toast.makeText(
                context,
                "Backup restaurado com sucesso",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    LaunchedEffect(Unit) {

        val db = DatabaseProvider.getDatabase(context)

        totalClientes =
            db.clienteDao()
                .listarTodos()
                .size

        totalEquipamentos =
            db.equipamentoDao()
                .listarTodos()
                .size

        totalAssistencias =
            db.assistenciaDao()
                .listarTodas()
                .size

        assistenciasPendentes =
            db.assistenciaDao()
                .contarPendentes()

        assistenciasConcluidas =
            db.assistenciaDao()
                .contarConcluidas()
    }




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //LOGOTIPO EMPRESA
        Image(
            painter = painterResource(id = R.drawable.logo_gef),
            contentDescription = "Logo GEF",
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(0.7f)
        )


        /*
        //ICONE CHAVE INGLESA
        Icon(
            imageVector = Icons.Default.Build,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
*/
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Assistência Técnica",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))



        //CARD ASSISTÊNCIAS
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE3F2FD)

                    ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onAssistenciasClick()
                }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "🔧 Assistências",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "$totalAssistencias Registadas",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Pendentes: $assistenciasPendentes"
                )

                Text(
                    text = "Concluídas: $assistenciasConcluidas"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        //CARD CLIENTES
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F0FE)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClientesClick()
                }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "👤 Clientes",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "$totalClientes Registados",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        //CARD EQUIPAMENTOS
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF4F7)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onEquipamentosClick()
                }

        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "💻 Equipamentos",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "$totalEquipamentos Registados",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //BOTÕES

        Button(
            onClick = onNovaAssistenciaClick,
            modifier = Modifier.fillMaxWidth(0.65f),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 6.dp
            )
        )
        {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Nova Assistência",
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                val ficheiro = criarBackup(context)

                Toast.makeText(
                    context,
                    "Backup criado: ${ficheiro.name}",
                    Toast.LENGTH_LONG
                ).show()


            }
        ) {
            Text("Backup")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {

                val ficheiro = criarBackup(context)

                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    ficheiro
                )

                val intent = Intent(Intent.ACTION_SEND).apply {

                    type = "application/octet-stream"

                    putExtra(
                        Intent.EXTRA_STREAM,
                        uri
                    )

                    addFlags(
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )
                }

                context.startActivity(
                    Intent.createChooser(
                        intent,
                        "Partilhar Backup"
                    )
                )

            }
        ) {
            Text("Partilhar Backup")
        }

        Button(
            onClick = {

                launcherRestaurar.launch(
                    arrayOf("*/*")
                )

            }
        ) {
            Text("Restaurar Backup")
        }

/*
        //BOTÕES ANTIGOS
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAssistenciasClick
        ) {
            Text("Ver Assistências")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button (
            onClick = onClientesClick
        ) {
            Text("Clientes")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onEquipamentosClick
        ) {
            Text("Equipamentos")
        }

        Spacer(modifier = Modifier.height(16.dp))
*/
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    RegistarAssistênciaTheme {
        HomeScreen(
            onNovaAssistenciaClick = {},
            onAssistenciasClick = {},
            onClientesClick = {},
            onEquipamentosClick = {}
        )
    }
}