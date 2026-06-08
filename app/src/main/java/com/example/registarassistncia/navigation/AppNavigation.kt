package com.example.registarassistncia.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registarassistncia.screens.HomeScreen
import com.example.registarassistncia.screens.ListaAssistenciasScreen
import com.example.registarassistncia.screens.ClienteScreen
import com.example.registarassistncia.screens.DetalhesAssistenciaScreen
import com.example.registarassistncia.screens.EquipamentoScreen
import com.example.registarassistncia.screens.ListaClientesScreen
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
                    navController.navigate(Routes.LISTA_CLIENTES)
                },
                onEquipamentosClick = {
                    navController.navigate(Routes.EQUIPAMENTOS)
                }
            )
        }
        composable(Routes.ASSISTENCIAS) {
            ListaAssistenciasScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                        onDetalhesClick = {
                    navController.navigate(Routes.DETALHE_ASSISTENCIA)
                }
            )
        }
        composable(Routes.CLIENTES) {
            ClienteScreen(
                onBackClick = {
                    navController.popBackStack()
                }

            )
        }
        composable(Routes.EQUIPAMENTOS) {
            EquipamentoScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.NOVA_ASSISTENCIA) {
            NovaAssistenciaScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.DETALHE_ASSISTENCIA) {
            DetalhesAssistenciaScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.NOVO_CLIENTE) {}

        composable(Routes.LISTA_CLIENTES) {
            ListaClientesScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNovoClienteClick = {
                    navController.navigate(Routes.CLIENTES)
                }
            )
        }
        composable(Routes.NOVO_EQUIPAMENTO) {}
    }
}

