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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.text.Normalizer
import java.util.Date
import java.util.Locale
import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.AssistenciaEntity


data class AssistenciaLista(
    val assistencia: AssistenciaEntity,
    val clienteNome: String,
    val equipamentoNome: String
)

fun formatarData(timestamp: String): String {

    return try {

        val data = Date(timestamp.toLong())

        SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            Locale.getDefault()
        ).format(data)

    } catch (e: Exception) {

        ""
    }
}

fun removerAcentos(texto: String): String {

    return Normalizer
        .normalize(texto, Normalizer.Form.NFD)
        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
}


@Composable
fun ListaAssistenciasScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onDetalhesClick: (Int) -> Unit
) {

    //Variáveis
    val context = LocalContext.current

    val assistencias = remember {
        mutableStateListOf<AssistenciaLista>()
    }

    var pesquisa by remember {
        mutableStateOf("")
    }

    val pesquisaNormalizada =
        removerAcentos(pesquisa.lowercase())

    val assistenciasFiltradas = assistencias.filter {

                removerAcentos(it.clienteNome.lowercase()).contains(pesquisaNormalizada) ||

                removerAcentos(it.equipamentoNome.lowercase()).contains(pesquisaNormalizada) ||

                removerAcentos(it.assistencia.estado.lowercase()).contains(pesquisaNormalizada) ||

                removerAcentos(it.assistencia.problema.lowercase()).contains(pesquisaNormalizada)
    }


    LaunchedEffect(Unit) {

        val db = DatabaseProvider.getDatabase(context)

        assistencias.clear()

        db.assistenciaDao()
            .listarTodas()
            .forEach { assistencia ->

                val cliente =
                    db.clienteDao()
                        .obterPorId(assistencia.clienteId)

                val equipamento =
                    db.equipamentoDao()
                        .obterPorId(assistencia.equipamentoId)

                assistencias.add(
                    AssistenciaLista(
                        assistencia = assistencia,
                        clienteNome = cliente.nome,
                        equipamentoNome =
                            "${equipamento?.marca ?: ""} ${equipamento?.modelo ?: ""}"
                    )
                )
            }
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

        OutlinedTextField(
            value = pesquisa,
            onValueChange = {
                pesquisa = it
            },
            label = {
                Text("Procurar assistência")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "${assistenciasFiltradas.size} Registadas",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )

        if (assistenciasFiltradas.isEmpty()) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nenhuma assistência encontrada",
                style = MaterialTheme.typography.bodyLarge
            )
        }

    Spacer(modifier = Modifier.height(8.dp))





        assistenciasFiltradas.forEach { item ->

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
                    onDetalhesClick(item.assistencia.id)
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

                    Text(item.clienteNome)
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        imageVector = Icons.Default.Devices,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(item.equipamentoNome)
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
                        text = item.assistencia.estado,
                        color = when {
                            item.assistencia.estado.contains("Diagnóstico") ->
                                Color(0xFFFF9800)

                            item.assistencia.estado.contains("Concluída") ->
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

                    Text(
                        formatarData(
                            item.assistencia.dataEntrada
                        )
                    )
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
