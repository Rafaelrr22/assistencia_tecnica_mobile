package com.example.registarassistncia.utils

import android.content.Context
import android.net.Uri
import java.io.FileOutputStream
import com.example.registarassistncia.model.BackupData
import com.example.registarassistncia.repository.AssistenciaRepository
import com.example.registarassistncia.repository.ClienteRepository
import com.example.registarassistncia.repository.EquipamentoRepository
import com.google.gson.Gson
import java.io.File


suspend fun criarBackup(
    context: Context
): File {

    val clienteRepository = ClienteRepository()

    val equipamentoRepository = EquipamentoRepository()

    val assistenciaRepository = AssistenciaRepository()

    val backup = BackupData(

        clientes =
            clienteRepository.obterClientes(),

        equipamentos =
            equipamentoRepository.obterEquipamentos(),

        assistencias =
            assistenciaRepository.obterAssistencias()
    )

    val gson = Gson()

    val json =
        gson.toJson(backup)

    val backupFile = File(
        context.getExternalFilesDir(null),
        "backup_assistencias.json"
    )

    backupFile.writeText(json)

    return backupFile
}

suspend fun restaurarBackup(
    context: Context,
    uri: Uri
) {

    val json =
        context.contentResolver
            .openInputStream(uri)
            ?.bufferedReader()
            ?.use {
                it.readText()
            } ?: return

    val gson = Gson()

    val backup =
        gson.fromJson(
            json,
            BackupData::class.java
        )

    val clienteRepository =
        ClienteRepository()

    val equipamentoRepository =
        EquipamentoRepository()

    val assistenciaRepository =
        AssistenciaRepository()

    // Apagar dados atuais

    assistenciaRepository.apagarTodasAssistencias()

    equipamentoRepository.apagarTodosEquipamentos()

    clienteRepository.apagarTodosClientes()

    // Restaurar clientes

    backup.clientes.forEach {

        clienteRepository.restaurarCliente(it)

    }

    // Restaurar equipamentos

    backup.equipamentos.forEach {

        equipamentoRepository.restaurarEquipamento(it)

    }

    // Restaurar assistências

    backup.assistencias.forEach {

        assistenciaRepository.restaurarAssistencia(it)

    }
}