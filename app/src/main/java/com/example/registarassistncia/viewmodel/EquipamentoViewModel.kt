package com.example.registarassistncia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registarassistncia.data.entity.ClienteEntity
import com.example.registarassistncia.data.entity.EquipamentoEntity
import com.example.registarassistncia.repository.ClienteRepository
import com.example.registarassistncia.repository.EquipamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EquipamentoViewModel : ViewModel() {

    private val repository = EquipamentoRepository()
    private val clienteRepository = ClienteRepository()

    private val _equipamentos =
        MutableStateFlow<List<EquipamentoEntity>>(emptyList())

    val equipamentos: StateFlow<List<EquipamentoEntity>> =
        _equipamentos.asStateFlow()

    private val _clientes =
        MutableStateFlow<List<ClienteEntity>>(emptyList())

    val clientes: StateFlow<List<ClienteEntity>> =
        _clientes.asStateFlow()

    init {
        carregarClientes()
        carregarEquipamentos()
    }

    fun carregarClientes() {

        viewModelScope.launch {

            _clientes.value =
                clienteRepository.obterClientes()
        }
    }

    fun carregarEquipamentos() {

        viewModelScope.launch {

            _equipamentos.value =
                repository.obterEquipamentos()
        }
    }

    fun obterEquipamento(
        documentId: String,
        onResult: (EquipamentoEntity?) -> Unit
    ) {

        viewModelScope.launch {

            onResult(
                repository.obterEquipamento(documentId)
            )
        }
    }

    fun guardarEquipamento(
        equipamento: EquipamentoEntity,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            val sucesso =
                if (equipamento.documentId.isBlank()) {
                    repository.adicionarEquipamento(equipamento)
                } else {
                    repository.atualizarEquipamento(equipamento)
                    true
                }

            onResult(sucesso)

            carregarEquipamentos()
        }
    }
}