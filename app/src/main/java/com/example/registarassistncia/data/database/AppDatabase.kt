package com.example.registarassistncia.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registarassistncia.data.dao.EquipamentoDao
import com.example.registarassistncia.data.entity.AssistenciaEntity
import com.example.registarassistncia.data.entity.EquipamentoEntity
import com.example.registarassistncia.data.dao.AssistenciaDao


@Database(
    entities = [
        EquipamentoEntity::class,
        AssistenciaEntity::class,
    ],

    version = 3,
    exportSchema = false
)

    abstract class AppDatabase : RoomDatabase() {

        abstract fun equipamentoDao(): EquipamentoDao

        abstract fun assistenciaDao(): AssistenciaDao
    }