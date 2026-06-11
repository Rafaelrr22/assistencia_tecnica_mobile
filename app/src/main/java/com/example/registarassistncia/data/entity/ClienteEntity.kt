package com.example.registarassistncia.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "clientes",
    indices = [
        Index(value = ["nif"], unique = true)
    ]
)
data class ClienteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,

    val telefone: String,

    val email: String,

    val nif: String,

    val morada: String,

    val tipoCliente: String

)