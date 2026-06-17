package com.example.registarassistncia.utils

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

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

fun restaurarBackup(
    context: Context,
    uri: Uri
) {
    val dbFile = context.getDatabasePath("assistencia_db")

    context.contentResolver.openInputStream(uri)?.use { input ->
        FileOutputStream(dbFile).use { output ->
            input.copyTo(output)
        }
    }
}