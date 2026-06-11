package com.example.registarassistncia.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.registarassistncia.data.entity.ClienteEntity

@Dao
interface ClienteDao {

    @Insert
    suspend fun  inserir (cliente: ClienteEntity)

    @Query("SELECT * FROM clientes")
    suspend fun listarTodos(): List<ClienteEntity>

    @Update
    suspend fun atualizar (cliente: ClienteEntity)

    @Delete
    suspend fun apagar (cliente: ClienteEntity)
}