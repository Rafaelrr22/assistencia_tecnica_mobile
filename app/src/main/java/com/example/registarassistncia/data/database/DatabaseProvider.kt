package com.example.registarassistncia.data.database

import android.content.Context
import androidx.room.Room


object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {

        return INSTANCE ?: synchronized(this) {

        val instance = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "assistencia_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        INSTANCE = instance

        instance
        }
    }

    fun fecharDatabase() {

        INSTANCE?.close()

        INSTANCE = null
    }
}