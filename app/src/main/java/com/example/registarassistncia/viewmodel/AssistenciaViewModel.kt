package com.example.registarassistncia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registarassistncia.data.entity.AssistenciaEntity
import com.example.registarassistncia.data.entity.ClienteEntity
import com.example.registarassistncia.data.entity.EquipamentoEntity
import com.example.registarassistncia.repository.AssistenciaRepository
import com.example.registarassistncia.repository.ClienteRepository
import com.example.registarassistncia.repository.EquipamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AssistenciaViewModel : ViewModel() {

    private val assistenciaRepository = AssistenciaRepository()
    private val clienteRepository = ClienteRepository()
    private val equipamentoRepository = EquipamentoRepository()

    private val _assistencias =
        MutableStateFlow<List<AssistenciaEntity>>(emptyList())

    val assistencias: StateFlow<List<AssistenciaEntity>> =
        _assistencias.asStateFlow()

    private val _clientes =
        MutableStateFlow<List<ClienteEntity>>(emptyList())

    val clientes: StateFlow<List<ClienteEntity>> =
        _clientes.asStateFlow()

    private val _equipamentos =
        MutableStateFlow<List<EquipamentoEntity>>(emptyList())

    val equipamentos: StateFlow<List<EquipamentoEntity>> =
        _equipamentos.asStateFlow()

    init {
        carregarAssistencias()
        carregarClientes()
        carregarTodosEquipamentos()
    }

    fun carregarAssistencias() {

        viewModelScope.launch {

            _assistencias.value =
                assistenciaRepository.obterAssistencias()
        }
    }

    fun carregarClientes() {

        viewModelScope.launch {

            _clientes.value =
                clienteRepository.obterClientes()
        }
    }

    fun carregarEquipamentos(clienteDocumentId: String) {

        viewModelScope.launch {

            _equipamentos.value =
                equipamentoRepository
                    .obterEquipamentosPorCliente(clienteDocumentId)
        }
    }

    fun carregarTodosEquipamentos() {

        viewModelScope.launch {

            _equipamentos.value =
                equipamentoRepository.obterEquipamentos()
        }
    }

    fun obterAssistencia(
        documentId: String,
        onResult: (AssistenciaEntity?) -> Unit
    ) {

        viewModelScope.launch {

            onResult(
                assistenciaRepository
                    .obterAssistencia(documentId)
            )
        }
    }

    fun guardarAssistencia(
        assistencia: AssistenciaEntity,
        onFinish: () -> Unit
    ) {

        viewModelScope.launch {

            try {

                if (assistencia.documentId.isBlank()) {
                    assistenciaRepository.adicionarAssistencia(assistencia)
                } else {
                    assistenciaRepository.atualizarAssistencia(assistencia)
                }

                _assistencias.value =
                    assistenciaRepository.obterAssistencias()

                onFinish()

            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}