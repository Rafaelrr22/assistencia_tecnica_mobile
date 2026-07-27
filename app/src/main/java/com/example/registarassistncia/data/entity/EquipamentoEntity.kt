package com.example.registarassistncia.data.entity

data class EquipamentoEntity(

    var documentId: String = "",

    val clienteDocumentId: String = "",

    val marca: String = "",

    val modelo: String = "",

    val numeroSerie: String = "",

    val tipoEquipamento: String = ""
)