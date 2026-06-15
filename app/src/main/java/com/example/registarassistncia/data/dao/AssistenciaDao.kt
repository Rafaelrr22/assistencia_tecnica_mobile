package com.example.registarassistncia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.registarassistncia.data.entity.AssistenciaEntity


@Dao
interface AssistenciaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserir(assistencia: AssistenciaEntity): Long

    @Update
    suspend fun atualizar(assistencia: AssistenciaEntity)

    @Delete
    suspend fun apagar(assistencia: AssistenciaEntity)

    @Query("SELECT * FROM assistencias ORDER BY dataEntrada DESC")
    suspend fun listarTodas(): List<AssistenciaEntity>

    @Query("SELECT * FROM assistencias WHERE id = :id")
    suspend fun obterPorId(id: Int): AssistenciaEntity?

}