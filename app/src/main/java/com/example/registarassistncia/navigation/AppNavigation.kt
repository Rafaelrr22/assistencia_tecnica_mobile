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
import com.example.registarassistncia.screens.ListaEquipamentoScreen
import com.example.registarassistncia.screens.NovaAssistenciaScreen
import com.example.registarassistncia.screens.DetalhesClienteScreen
import com.example.registarassistncia.screens.DetalhesEquipamentoScreen

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
                    navController.navigate(Routes.LISTA_EQUIPAMENTO)
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
                },
                onClienteGuardado = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.EQUIPAMENTOS) {
            EquipamentoScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onEquipamentoGuardado = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.NOVA_ASSISTENCIA) {
            NovaAssistenciaScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onAssistenciaGuardada = {
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
                onDetalhesClienteClick = {
                        clienteId ->
                    navController.navigate(
                        "${Routes.DETALHES_CLIENTE_BASE}/$clienteId"
                    )
                },
                onNovoClienteClick = {
                    navController.navigate(Routes.CLIENTES)
                }
            )
        }

        composable(
            route = Routes.DETALHES_CLIENTE
        ) { backStackEntry ->

            val clienteId =
                backStackEntry.arguments
                    ?.getString("clienteId")
                    ?.toIntOrNull() ?: 0

            DetalhesClienteScreen(
                clienteId = clienteId,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditarClick = { id ->

                    navController.navigate(
                        "${Routes.EDITAR_CLIENTE_BASE}/$id"
                    )
                }
            )
        }

        composable(
            route = Routes.EDITAR_CLIENTE
        ) { backStackEntry ->

            val clienteId =
                backStackEntry.arguments
                    ?.getString("clienteId")
                    ?.toIntOrNull()

            ClienteScreen(
                clienteId = clienteId,
                onBackClick = {
                    navController.popBackStack()
                },
                onClienteGuardado = {
                    navController.popBackStack()
                }
            )
        }


        composable(Routes.NOVO_EQUIPAMENTO) {}

        composable(Routes.LISTA_EQUIPAMENTO) {
            ListaEquipamentoScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onDetalhesEquipamentoClick = { equipamentoId ->
                    navController.navigate("${Routes.DETALHES_EQUIPAMENTO_BASE}/$equipamentoId")
                },
                onNovoEquipamentoClick = {
                    navController.navigate(Routes.EQUIPAMENTOS)
                }
            )
        }



        composable(
            route = Routes.DETALHES_EQUIPAMENTO
        ) { backStackEntry ->

            val equipamentoId =
                backStackEntry.arguments
                    ?.getString("equipamentoId")
                    ?.toIntOrNull() ?: 0

            DetalhesEquipamentoScreen(
                equipamentoId = equipamentoId,
                onBackClick = {
                    navController.popBackStack()
                },
                onEditarClick = { id ->

                    navController.navigate(
                        "${Routes.EDITAR_EQUIPAMENTO_BASE}/$id"
                    )
                }
            )
        }

        composable(
            route = Routes.EDITAR_EQUIPAMENTO
        ) { backStackEntry ->

            val equipamentoId =
                backStackEntry.arguments
                    ?.getString("equipamentoId")
                    ?.toIntOrNull()

            EquipamentoScreen(
                equipamentoId = equipamentoId,
                onBackClick = {
                    navController.popBackStack()
                },
                onEquipamentoGuardado = {
                    navController.popBackStack()
                }
            )
        }
    }
}

