package com.example.registarassistncia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.registarassistncia.data.entity.ClienteEntity


@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserir(cliente: ClienteEntity): Long

    @Query("SELECT * FROM clientes ORDER BY nome ASC")
    suspend fun listarTodos(): List<ClienteEntity>

    @Query ( "SELECT * FROM clientes WHERE id = :id")
    suspend fun obterPorId(id: Int): ClienteEntity

    @Update
    suspend fun atualizar (cliente: ClienteEntity)

    @Delete
    suspend fun apagar (cliente: ClienteEntity)
}