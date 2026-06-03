package com.example.registarassistncia.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registarassistncia.screens.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME



    ) {
        composable(Routes.HOME) {
            HomeScreen()
        }
        composable(Routes.ASSISTENCIAS) {}
        composable(Routes.CLIENTES) {}
        composable(Routes.EQUIPAMENTOS) {}
        composable(Routes.NOVA_ASSISTENCIA) {}
        composable(Routes.DETALHE_ASSISTENCIA) {}
        composable(Routes.NOVO_CLIENTE) {}
        composable(Routes.NOVO_EQUIPAMENTO) {}
    }
}

