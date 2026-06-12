package com.example.registarassistncia.screens


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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.EquipamentoEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipamentoScreen (
    modifier: Modifier = Modifier,
    equipamentoId: Int? = null,
    onBackClick: () -> Unit,
    onEquipamentoGuardado: () -> Unit
)

{
    //VARIAVEIS
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var numSerie by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    val tiposEquipamento = listOf(
        "PORTÁTIL",
        "DESKTOP",
        "IMPRESSORA",
        "MONITOR",
        "SERVIDOR",
        "OUTRO"
    )

    var tipoEquipamento by remember { mutableStateOf("PORTÁTIL") }



    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
    )
    {
        Text(
            text = "Novo Equipamento",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF4F7)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                //Campo Cliente Associado


                // Campo Marca

                OutlinedTextField(
                    value = marca,
                    onValueChange = { marca = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Business,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Marca")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Campo Modelo

                OutlinedTextField(
                    value = modelo,
                    onValueChange = { modelo = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Computer,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Modelo")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Campo Numero Serie

                OutlinedTextField(
                    value = numSerie,
                    onValueChange = {numSerie = it},
                    label = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Tag,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text("Número Série")
                        }
                    },
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(8.dp))

                // Campo TipoEquipamento (com dropdown)

                val tiposEquipamento = listOf(
                    "PORTÁTIL",
                    "DESKTOP",
                    "IMPRESSORA",
                    "MONITOR",
                    "SERVIDOR",
                    "OUTRO"
                )

                //TIPO EQUIPAMENTO

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {expanded = !expanded}
                )
                {
                    OutlinedTextField(
                        value = tipoEquipamento,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Category,
                                    contentDescription = null
                                )

                                Spacer(modifier = Modifier.width(4.dp))

                                Text("Tipo de Equipamento")
                            }
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {expanded = false}
                    )
                    {
                        tiposEquipamento.forEach {  tipo ->
                            DropdownMenuItem(
                                text = {Text(tipo)},
                                onClick = {
                                    tipoEquipamento = tipo
                                    expanded = false
                                }
                            )
                        }

                    }
                }


            }
        }



        Spacer(modifier = modifier.height(16.dp))

        Button(
            onClick = {

                scope.launch {

                    val db = DatabaseProvider.getDatabase(context)

                    val resultado =
                        db.equipamentoDao().inserir(
                            EquipamentoEntity(
                                marca = marca,
                                modelo = modelo,
                                numeroSerie = numSerie,
                                tipoEquipamento = tipoEquipamento
                            )
                        )

                    if (resultado == -1L) {

                        Toast.makeText(
                            context,
                            "Já existe um equipamento com este número de série",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {

                        Toast.makeText(
                            context,
                            "Equipamento criado com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()

                        onEquipamentoGuardado()

                        marca = ""
                        modelo = ""
                        numSerie = ""
                        tipoEquipamento = "PORTÁTIL"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {

            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Guardar Equipamento")
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.fillMaxWidth(0.6f)
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