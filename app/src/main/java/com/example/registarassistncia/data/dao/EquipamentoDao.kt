package com.example.registarassistncia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.registarassistncia.data.entity.EquipamentoEntity

@Dao
interface EquipamentoDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserir(equipamento: EquipamentoEntity): Long


    @Update()
    suspend fun atualizar(equipamento: EquipamentoEntity)


    @Delete()
    suspend fun apagar(equipamento: EquipamentoEntity)


    @Query("SELECT * FROM equipamentos ORDER BY marca ASC")
    suspend fun listarTodos(): List<EquipamentoEntity>

    @Query(
        "SELECT * FROM equipamentos WHERE clienteId = :clienteId"
    )
    suspend fun listarPorCliente(clienteId: Int): List<EquipamentoEntity>


    @Query("SELECT * FROM equipamentos WHERE id = :id")
    suspend fun obterPorId(id: Int): EquipamentoEntity?
}