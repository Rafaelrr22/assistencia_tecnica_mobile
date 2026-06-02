package com.example.registarassistncia.model

data class Equipamento(
    val id: Int = 0,
    val clienteId: Int,
    val marca: String,
    val modelo: String,
    val numeroSerie: String,
    val tipo: TipoEquipamento
)

enum class TipoEquipamento {
    PORTATIL,
    DESKTOP,
    IMPRESSORA,
    MONITOR,
    SERVIDOR,
    OUTRO
}
