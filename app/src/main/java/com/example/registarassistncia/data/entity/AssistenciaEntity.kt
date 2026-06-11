package com.example.registarassistncia.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assistencias")

data class AssistenciaEntity(

    @PrimaryKey(true)
    val id: Int = 0,

    val clinte: String,

    val equipamento: String,

    val problema: String,

    val diagnostico: String,

    val solucao: String,

    val estado: String,

    val orcamento: Double,

    val dataPrevista: String
)