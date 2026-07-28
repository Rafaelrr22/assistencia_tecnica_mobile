package com.example.registarassistncia.model

import com.example.registarassistncia.data.entity.AssistenciaEntity
import com.example.registarassistncia.data.entity.ClienteEntity
import com.example.registarassistncia.data.entity.EquipamentoEntity

data class BackupData(

    val clientes: List<ClienteEntity> = emptyList(),

    val equipamentos: List<EquipamentoEntity> = emptyList(),

    val assistencias: List<AssistenciaEntity> = emptyList()

)