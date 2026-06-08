package com.example.registarassistncia.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement



@Composable
fun DetalhesAssistenciaScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
){

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ){
        Text(
            text = "Detalhes Assistência",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        //CAMPOS

        // Campo Cliente
        Text(
            text = "Cliente: José Silva"
        )

        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO EQUIPAMENTO

        Text(
            text = "Equipamento: Portátil Asus"
        )

        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO DATA

        Text(
            text = "Data: 08/06/2026"
        )

        Spacer(modifier = Modifier.height(16.dp))

        //CAMPO PROBLEMA
        Text(
            text = "Problema: ",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Não liga."
        )

        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO DIAGNOSTICO
        Text(
            text = "Diagnóstico: ",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Por diagnosticar."
        )

        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO SOLUÇÃO
        Text(
            text = "Solução: ",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Sem solução aplicada."
        )

        Spacer(modifier = Modifier.height(8.dp))





        //CAMPO ESTADO
        Text(
            text = "Estado: ",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "EM_DIAGNOSTICO"
        )

        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO ORÇAMENTO
        Text(
            text = "Orçamento:",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "65€"
        )


        Spacer(modifier = Modifier.height(8.dp))

        //CAMPO TEMPO GASTO
        Text(
            text = "Tempo gasto: ",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "45min"
        )




        //BOTÕES
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Editar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Apagar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onBackClick
        ){
            Text ("Voltar")
        }
    }
}