package com.example.registarassistncia.model


data class Cliente(
    val id: Int = 0,
    val nome: String,
    val telefone: String,
    val email: String,
    val nif: String?,
    val morada: String,
    val tipoCliente: TipoCliente
)

enum class TipoCliente {
    PARTICULAR,
    EMPRESA
}