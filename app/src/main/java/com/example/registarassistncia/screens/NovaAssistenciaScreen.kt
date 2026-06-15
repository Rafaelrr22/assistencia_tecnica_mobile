package com.example.registarassistncia.screens

import android.widget.Toast
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.AssistenciaEntity
import com.example.registarassistncia.data.entity.ClienteEntity
import com.example.registarassistncia.data.entity.EquipamentoEntity
import kotlinx.coroutines.launch
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovaAssistenciaScreen(
    modifier: Modifier = Modifier,
    assistenciaId: Int? = null,
    onBackClick: () -> Unit,
    onAssistenciaGuardada: () -> Unit
) {

    //VARIÁVEIS
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val clientes = remember {
        mutableStateListOf<ClienteEntity>()
    }

    val equipamentos = remember {
        mutableStateListOf<EquipamentoEntity>()
    }

    var clienteSelecionadoId by remember {
        mutableStateOf<Int?>(null)
    }

    var equipamentoSelecionadoId by remember {
        mutableStateOf<Int?>(null)
    }

    var problema by remember {
        mutableStateOf("")
    }

    var diagnostico by remember {
        mutableStateOf(value = "")
    }

    var solucao by remember {
        mutableStateOf("")
    }

    var orcamento by remember {
        mutableStateOf("")
    }

    var estado by remember {
        mutableStateOf("")
    }

    var dataPrevista by remember {
        mutableStateOf("15/06/2026")
    }

    var clienteExpanded by remember {
        mutableStateOf(false)
    }

    var equipamentoExpanded by remember {
        mutableStateOf(false)
    }

    var assistencia by remember {
        mutableStateOf<AssistenciaEntity?>(null)
    }



    LaunchedEffect(Unit) {

        val db = DatabaseProvider.getDatabase(context)

        clientes.clear()

        clientes.addAll(
            db.clienteDao().listarTodos()
        )
    }

    LaunchedEffect(assistenciaId) {

        if (assistenciaId != null) {

            val db = DatabaseProvider.getDatabase(context)

            val assistencia =
                db.assistenciaDao()
                    .obterPorId(assistenciaId)

            assistencia?.let {

                clienteSelecionadoId = it.clienteId

                equipamentoSelecionadoId = it.equipamentoId

                equipamentos.clear()

                equipamentos.addAll(
                    db.equipamentoDao()
                        .listarPorCliente(it.clienteId)
                )

                problema = it.problema

                diagnostico = it.diagnostico

                solucao = it.solucao

                orcamento = it.orcamento.toString()

                estado = it.estado


            }
        }
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
            text = "Nova Assistência",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Clientes carregados: ${clientes.size}"
        )
        Spacer(modifier = Modifier.height(12.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                //CAMPO CLIENTE
                Text(
                    text = "Cliente",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = clienteExpanded,
                    onExpandedChange = {
                        clienteExpanded = !clienteExpanded
                    }
                ) {

                    OutlinedTextField(
                        value = clientes
                            .find { it.id == clienteSelecionadoId }
                            ?.nome ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Cliente")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = clienteExpanded,
                        onDismissRequest = {
                            clienteExpanded = false
                        }
                    ) {

                        clientes.forEach { cliente ->

                            DropdownMenuItem(
                                text = {
                                    Text(cliente.nome)
                                },
                                onClick = {

                                    clienteSelecionadoId = cliente.id

                                    equipamentoSelecionadoId = null

                                    clienteExpanded = false

                                    scope.launch {

                                        val db =
                                            DatabaseProvider.getDatabase(context)

                                        equipamentos.clear()

                                        equipamentos.addAll(
                                            db.equipamentoDao()
                                                .listarPorCliente(cliente.id)
                                        )
                                    }
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                //CAMPO EQUIPAMENTO
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Equipamento",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = equipamentoExpanded,
                    onExpandedChange = {
                        equipamentoExpanded = !equipamentoExpanded
                    }
                ) {

                    OutlinedTextField(
                        value = equipamentos
                            .find {
                                it.id == equipamentoSelecionadoId
                            }
                            ?.let {
                                "${it.marca} ${it.modelo}"
                            } ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Equipamento")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = equipamentoExpanded,
                        onDismissRequest = {
                            equipamentoExpanded = false
                        }
                    ) {

                        equipamentos.forEach { equipamento ->

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "${equipamento.marca} ${equipamento.modelo}"
                                    )
                                },
                                onClick = {

                                    equipamentoSelecionadoId =
                                        equipamento.id

                                    equipamentoExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))


                //CAMPO PROBLEMA

                OutlinedTextField(
                    value = problema,
                    onValueChange = {
                        problema = it
                    },
                    label = {
                        Text("Problema")
                    },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))


                //CAMPO DIAGNÓSTICO


                OutlinedTextField(
                    value = diagnostico,
                    onValueChange = { diagnostico = it },
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Diagnóstico")
                        }
                    },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                //CAMOP SOLUÇÃO


                OutlinedTextField(
                    value = solucao,
                    onValueChange = { solucao = it },
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.BuildCircle,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Solução")
                        }
                    },
                    minLines = 4,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                //CAMPO ORÇAMENTO


                OutlinedTextField(
                    value = orcamento,
                    onValueChange = { orcamento = it },
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.AttachMoney,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Orçamento")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                //CAMPO ESTADO


                OutlinedTextField(
                    value = estado,
                    onValueChange = {estado = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Estado")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                // CAMPO DATA PREVISTA


                OutlinedTextField(
                    value = dataPrevista,
                    onValueChange = { dataPrevista = it },
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Data Prevista")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }





        Spacer(modifier = Modifier.height(16.dp))


        //BOTÕES
        Button(
            onClick = {

                scope.launch {

                    val db = DatabaseProvider.getDatabase(context)

                    if (clienteSelecionadoId == null) {

                        Toast.makeText(
                            context,
                            "Selecione um cliente",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@launch
                    }

                    if (equipamentoSelecionadoId == null) {

                        Toast.makeText(
                            context,
                            "Selecione um equipamento",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@launch
                    }

                    if (assistenciaId == null) {

                        db.assistenciaDao().inserir(
                            AssistenciaEntity(
                                clienteId = clienteSelecionadoId!!,
                                equipamentoId = equipamentoSelecionadoId!!,
                                problema = problema,
                                estado = estado.ifBlank { "PENDENTE" },
                                diagnostico = diagnostico,
                                solucao = solucao,
                                orcamento = orcamento.toDoubleOrNull() ?: 0.0,
                                dataEntrada = System.currentTimeMillis().toString(),
                                dataSaida = null
                            )
                        )

                        Toast.makeText(
                            context,
                            "Assistência criada",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        db.assistenciaDao().atualizar(
                            AssistenciaEntity(
                                id = assistenciaId,
                                clienteId = clienteSelecionadoId!!,
                                equipamentoId = equipamentoSelecionadoId!!,
                                problema = problema,
                                estado = estado,
                                diagnostico = diagnostico,
                                solucao = solucao,
                                orcamento = orcamento.toDoubleOrNull() ?: 0.0,
                                dataEntrada = assistencia?.dataEntrada ?: "",
                                dataSaida = assistencia?.dataSaida
                            )
                        )

                        Toast.makeText(
                            context,
                            "Assistência atualizada",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    onAssistenciaGuardada()
                }
            },
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                if (assistenciaId == null)
                    "Guardar Assistência"
                else
                    "Atualizar Assistência"
            )
        }


        Button(
            onClick = onBackClick
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