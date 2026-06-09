package com.example.registarassistncia.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info

@Composable
fun DetalhesAssistenciaScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD)
        ),

        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Detalhes Assistência",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            //CAMPOS

            // Campo Cliente
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Cliente")
            }

            Text("José Silva")

            Spacer(modifier = Modifier.height(8.dp))

            //CAMPO EQUIPAMENTO

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Build,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Equipamento")
            }

            Text("Portátil Asus")

            Spacer(modifier = Modifier.height(8.dp))

            //CAMPO DATA

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text("Data: 08/06/2026")
            }

            Spacer(modifier = Modifier.height(16.dp))

            //CAMPO PROBLEMA
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text("Problema")
            }

            Text("Não liga")

            Spacer(modifier = Modifier.height(12.dp))

            //CAMPO DIAGNOSTICO
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text("Diagnóstico")
            }

            Text("Por diagnosticar")

            Spacer(modifier = Modifier.height(12.dp))

            //CAMPO SOLUÇÃO
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Handyman,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text("Solução")
            }

            Text("Sem solução Aplicada")

            Spacer(modifier = Modifier.height(12.dp))


            //CAMPO ESTADO
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Estado")
            }

            Text(

                text = "Em Diagnóstico",
                color = Color(0xFFFF9800)
            )


            Spacer(modifier = Modifier.height(12.dp))

            //CAMPO ORÇAMENTO
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.AttachMoney,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text("Orçamento")
            }

            Text("65 €")

            Spacer(modifier = Modifier.height(12.dp))

            //CAMPO TEMPO GASTO
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text("Tempo gasto")
            }

            Text("45 min")

            Spacer(modifier = Modifier.height(24.dp))


            //BOTÕES
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Editar")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Apagar")
            }

            Spacer(modifier = Modifier.height(8.dp))

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
    }

