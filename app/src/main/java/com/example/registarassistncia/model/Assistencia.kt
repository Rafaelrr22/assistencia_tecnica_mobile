package com.example.registarassistncia.model

import java.time.LocalDate


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
    val valOrcamento: Double?,
    val estado : EstadoAssistencia,
    val tempoGasto: Int = 0,
    val dataPrevistaEntrega: LocalDate?
)


enum class EstadoAssistencia {
    EM_DIAGNOSTICO,
    AGUARDAR_PECA,
    EM_REPARACAO,
    CONCLUIDA,
    ORCAMENTO_REJEITADO,
    AGUARDAR_APROVACAO,
    CANCELADA
}

