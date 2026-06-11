package com.example.registarassistncia.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipamentos")

data class EquipamentoEntity(

    @PrimaryKey(true)
    val id: Int = 0,

    val  marca: String,

    val modelo: String,

    val numeroSerie: String,

    val tipoEquipamento: String

)