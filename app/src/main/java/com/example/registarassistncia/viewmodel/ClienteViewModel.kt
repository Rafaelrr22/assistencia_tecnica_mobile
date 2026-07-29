package com.example.registarassistncia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registarassistncia.data.entity.ClienteEntity
import com.example.registarassistncia.repository.ClienteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClienteViewModel : ViewModel() {

    private val repository = ClienteRepository()

    private val _clientes =
        MutableStateFlow<List<ClienteEntity>>(emptyList())

    val clientes: StateFlow<List<ClienteEntity>>
            = _clientes.asStateFlow()

    init {
        carregarClientes()
    }

    fun carregarClientes() {

        viewModelScope.launch {

            _clientes.value =
                repository.obterClientes()

        }
    }

    fun obterCliente(
        documentId: String,
        onResult: (ClienteEntity?) -> Unit
    ) {

        viewModelScope.launch {

            onResult(
                repository.obterCliente(documentId)
            )
        }
    }
    fun guardarCliente(
        cliente: ClienteEntity,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            val sucesso =

                if (cliente.documentId.isBlank()) {
                    repository.adicionarCliente(cliente)
                } else {
                    repository.atualizarCliente(cliente)
                    true
                }

            onResult(sucesso)

            carregarClientes()
        }
    }

}