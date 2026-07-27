package com.example.registarassistncia.data.entity

data class AssistenciaEntity(

    var documentId: String = "",

    val clienteDocumentId: String = "",

    val equipamentoDocumentId: String = "",

    val problema: String = "",

    val estado: String = "",

    val diagnostico: String = "",

    val solucao: String = "",

    val orcamento: Double = 0.0,

    val dataEntrada: String = "",

    val dataSaida: String? = null
)