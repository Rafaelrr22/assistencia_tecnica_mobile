package com.example.registarassistncia.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.EquipamentoEntity

@Composable
fun DetalhesEquipamentoScreen(
    modifier: Modifier = Modifier,
    equipamentoId: Int,
    onBackClick: () -> Unit,
    onEditarClick: (Int) -> Unit
) {

    val context = LocalContext.current

    var equipamento by remember {
        mutableStateOf<EquipamentoEntity?>(null)
    }

    LaunchedEffect(equipamentoId) {

        val db = DatabaseProvider.getDatabase(context)

        equipamento =
            db.equipamentoDao().obterPorId(equipamentoId)
    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {

        Text(
            text = "Detalhes Equipamento",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF4F7)
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

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Computer, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Equipamento")
                }

                Text("${equipamento?.marca ?: ""} ${equipamento?.modelo ?: ""}")

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Folder, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Tipo")
                }
                Text(equipamento?.tipoEquipamento ?: "")

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Numbers, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Nº Série")
                }
                Text(equipamento?.numeroSerie ?: "")
            }
        }



        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                equipamento?.let {
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

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(Icons.Default.Delete, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Apagar")
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