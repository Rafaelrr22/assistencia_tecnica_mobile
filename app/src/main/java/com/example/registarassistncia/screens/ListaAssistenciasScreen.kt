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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.AssistenciaEntity



@Composable
fun ListaAssistenciasScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onDetalhesClick: (Int) -> Unit
) {

    //Variáveis
    val context = LocalContext.current

    val assistencias = remember {
        mutableStateListOf<AssistenciaEntity>()
    }

    LaunchedEffect(Unit) {

        val db = DatabaseProvider.getDatabase(context)

        assistencias.clear()

        assistencias.addAll(
            db.assistenciaDao().listarTodas()
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {



    Text(
        text = "Assistências",
        style = MaterialTheme.typography.headlineMedium
    )

    Text(
        text = "${assistencias.size} Registadas",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )

    Spacer(modifier = Modifier.height(8.dp))





    assistencias.forEach { assistencia ->

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
                    onDetalhesClick(assistencia.id)
                }
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Cliente ID: ${assistencia.clienteId}")
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Devices,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Cliente ID: ${assistencia.equipamentoId}")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = assistencia.estado,
                        color = when {
                            assistencia.estado.contains("Diagnóstico") ->
                                Color(0xFFFF9800)

                            assistencia.estado.contains("Concluída") ->
                                Color(0xFF4CAF50)

                            else ->
                                Color(0xFF2196F3)
                        }
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(assistencia.dataEntrada)
                }

            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(8.dp))

/*
     Button(
            onClick = onDetalhesClick
        )
     {
        Text("Ver detalhes")
     }
*/
    Button(
        onClick = onBackClick
    ) {
        Text("Voltar")
        }

    }
}
