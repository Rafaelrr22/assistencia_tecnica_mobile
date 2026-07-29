package com.example.registarassistncia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registarassistncia.repository.AssistenciaRepository
import com.example.registarassistncia.repository.ClienteRepository
import com.example.registarassistncia.repository.EquipamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val clienteRepository = ClienteRepository()
    private val equipamentoRepository = EquipamentoRepository()
    private val assistenciaRepository = AssistenciaRepository()

    private val _totalClientes = MutableStateFlow(0)
    val totalClientes: StateFlow<Int> = _totalClientes.asStateFlow()

    private val _totalEquipamentos = MutableStateFlow(0)
    val totalEquipamentos: StateFlow<Int> = _totalEquipamentos.asStateFlow()

    private val _totalAssistencias = MutableStateFlow(0)
    val totalAssistencias: StateFlow<Int> = _totalAssistencias.asStateFlow()

    private val _assistenciasPendentes = MutableStateFlow(0)
    val assistenciasPendentes: StateFlow<Int> = _assistenciasPendentes.asStateFlow()

    private val _assistenciasConcluidas = MutableStateFlow(0)
    val assistenciasConcluidas: StateFlow<Int> = _assistenciasConcluidas.asStateFlow()

    init {
        atualizarDashboard()
    }

    fun atualizarDashboard() {

        viewModelScope.launch {

            val clientes = clienteRepository.obterClientes()
            val equipamentos = equipamentoRepository.obterEquipamentos()
            val assistencias = assistenciaRepository.obterAssistencias()

            _totalClientes.value = clientes.size
            _totalEquipamentos.value = equipamentos.size
            _totalAssistencias.value = assistencias.size

            _assistenciasPendentes.value =
                assistencias.count { it.estado == "PENDENTE" }

            _assistenciasConcluidas.value =
                assistencias.count { it.estado == "CONCLUÍDA" }
        }
    }
}