package com.example.registarassistncia.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assistencias")

data class AssistenciaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val clinteId: Int,

    val equipamentoId: Int,

    val problema: String,

    val estado: String,

    val diagnostico: String,

    val solucao: String,

    val orcamento: Double,

    val dataEntrada: String,

    val dataSaida: String?
)