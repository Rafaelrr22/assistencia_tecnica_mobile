package com.example.registarassistncia.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "equipamentos",
    indices = [
        Index(value = ["numeroSerie"], unique = true)
    ])

data class EquipamentoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val clienteId: Int,

    val  marca: String,

    val modelo: String,

    val numeroSerie: String,

    val tipoEquipamento: String

)