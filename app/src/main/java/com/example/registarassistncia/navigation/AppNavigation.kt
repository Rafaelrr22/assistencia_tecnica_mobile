package com.example.registarassistncia.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registarassistncia.screens.HomeScreen
import com.example.registarassistncia.screens.ListaAssistenciasScreen
import com.example.registarassistncia.screens.ClienteScreen
import com.example.registarassistncia.screens.EquipamentoScreen
import com.example.registarassistncia.screens.NovaAssistenciaScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME



    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onNovaAssistenciaClick = {
                    navController.navigate(Routes.NOVA_ASSISTENCIA)
                },
                onAssistenciasClick = {
                    navController.navigate(Routes.ASSISTENCIAS)
                },
                onClientesClick = {
                    navController.navigate(Routes.CLIENTES)
                },
                onEquipamentosClick = {
                    navController.navigate(Routes.EQUIPAMENTOS)
                }
            )
        }
        composable(Routes.ASSISTENCIAS) {
            ListaAssistenciasScreen()
        }
        composable(Routes.CLIENTES) {
            ClienteScreen()
        }
        composable(Routes.EQUIPAMENTOS) {
            EquipamentoScreen()
        }
        composable(Routes.NOVA_ASSISTENCIA) {
            NovaAssistenciaScreen()
        }
        composable(Routes.DETALHE_ASSISTENCIA) {}
        composable(Routes.NOVO_CLIENTE) {}
        composable(Routes.NOVO_EQUIPAMENTO) {}
    }
}

