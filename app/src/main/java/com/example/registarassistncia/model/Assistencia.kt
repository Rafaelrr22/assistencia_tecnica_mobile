package com.example.registarassistncia.model



data class Assistencia(
    val id: Int = 0,
    val clienteId: Int,
    val tecnicoId: Int,
    val equipamentoId: Int,
    val data: String,
    val problema: String,
    val diagnostico: String?,
    val solucao: String?,
    val observacoes: String?,
    val estado : EstadoAssistencia,
    val tempoGasto: Int = 0
)

data class Cliente(
    val id: Int = 0,
    val nome: String,
    val telefone: String,
    val email: String,
    val nif: String?,
    val morada: String,
    val tipoCliente: TipoCliente
)

data class Tecnico(
    val id: Int = 0,
    val nome: String,
    val telefone: String
)

data class Equipamento(
    val id: Int = 0,
    val clienteId: Int,
    val marca: String,
    val modelo: String,
    val numeroSerie: String,
    val tipo: TipoEquipamento
)

enum class EstadoAssistencia {
    EM_DIAGNOSTICO,
    AGUARDAR_PECA,
    EM_REPARACAO,
    CONCLUIDA,
    CANCELADA
}

enum class TipoEquipamento {
    PORTATIL,
    DESKTOP,
    IMPRESSORA,
    MONITOR,
    SERVIDOR,
    OUTRO
}

enum class TipoCliente {
    PARTICULAR,
    EMPRESA
}