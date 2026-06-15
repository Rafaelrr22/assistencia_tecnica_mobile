package com.example.registarassistncia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registarassistncia.data.dao.ClienteDao
import com.example.registarassistncia.data.dao.EquipamentoDao
import com.example.registarassistncia.data.entity.AssistenciaEntity
import com.example.registarassistncia.data.entity.ClienteEntity
import com.example.registarassistncia.data.entity.EquipamentoEntity


@Database(
    entities = [
        ClienteEntity::class,
        EquipamentoEntity::class,
        AssistenciaEntity::class,
    ],

    version = 2,
    exportSchema = false
)

    abstract class AppDatabase : RoomDatabase() {

        abstract fun clienteDao(): ClienteDao

        abstract fun equipamentoDao(): EquipamentoDao
    }