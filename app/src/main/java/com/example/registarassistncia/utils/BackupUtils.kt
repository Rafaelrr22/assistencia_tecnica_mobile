package com.example.registarassistncia.utils

import android.content.Context
import java.io.File

fun criarBackup(context: Context): File {

    val dbFile = context.getDatabasePath("assistencia_db")

    val backupFile = File(
        context.getExternalFilesDir(null),
        "backup_assistencias.db"
    )

    dbFile.copyTo(
        backupFile,
        overwrite = true
    )

    return backupFile
}