package com.example.registarassistncia.screens

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

@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               onNovaAssistenciaClick: () -> Unit,
               onAssistenciasClick: () -> Unit,
               onClientesClick: () -> Unit,
               onEquipamentosClick: () -> Unit
              )
{

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Assistência Técnica",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))



        //CARD ASSISTÊNCIAS
        Card(
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

                Text("5 Ativas")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        //CARD CLIENTES
        Card(
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
                Text("20 Registados")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        //CARD EQUIPAMENTOS
        Card(
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
                Text("39 Registados")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        //BOTÕES

        Button(
            onClick = onNovaAssistenciaClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Nova Assistência")
        }

/*

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