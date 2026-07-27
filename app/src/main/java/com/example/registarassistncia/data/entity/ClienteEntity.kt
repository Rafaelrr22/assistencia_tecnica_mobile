package com.example.registarassistncia.data.entity

data class ClienteEntity(

    var documentId: String = "",

    val nome: String = "",

    val telefone: String = "",

    val email: String = "",

    val nif: String = "",

    val morada: String = "",

    val tipoCliente: String = ""

)