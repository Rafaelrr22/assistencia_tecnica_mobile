package com.example.registarassistncia.repository

import com.example.registarassistncia.data.entity.EquipamentoEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class EquipamentoRepository {

    private val firestore = FirebaseFirestore.getInstance()

    private val equipamentos =
        firestore.collection("equipamentos")

    suspend fun adicionarEquipamento(
        equipamento: EquipamentoEntity
    ): Boolean {

        val existe = equipamentos
            .whereEqualTo(
                "numeroSerie",
                equipamento.numeroSerie
            )
            .get()
            .await()

        if (!existe.isEmpty) {
            return false
        }

        val docRef = equipamentos.document()

        equipamento.documentId = docRef.id

        docRef.set(equipamento).await()

        return true
    }

    suspend fun obterEquipamentos(): List<EquipamentoEntity> {

        val snapshot = equipamentos.get().await()

        return snapshot.documents.mapNotNull { document ->

            document.toObject(
                EquipamentoEntity::class.java
            )?.apply {

                documentId = document.id
            }
        }
    }

    suspend fun obterEquipamentosPorCliente(
        clienteDocumentId: String
    ): List<EquipamentoEntity> {

        val snapshot = equipamentos
            .whereEqualTo(
                "clienteDocumentId",
                clienteDocumentId
            )
            .get()
            .await()

        return snapshot.documents.mapNotNull { document ->

            document.toObject(
                EquipamentoEntity::class.java
            )?.apply {

                documentId = document.id
            }
        }
    }

    suspend fun obterEquipamento(
        documentId: String
    ): EquipamentoEntity? {

        return equipamentos
            .document(documentId)
            .get()
            .await()
            .toObject(
                EquipamentoEntity::class.java
            )?.apply {

                this.documentId = documentId
            }
    }

    suspend fun atualizarEquipamento(
        equipamento: EquipamentoEntity
    ) {

        equipamentos
            .document(equipamento.documentId)
            .set(equipamento)
            .await()
    }

    suspend fun apagarEquipamento(
        documentId: String
    ) {

        equipamentos
            .document(documentId)
            .delete()
            .await()
    }

    suspend fun restaurarEquipamento(
        equipamento: EquipamentoEntity
    ) {

        equipamentos
            .document(equipamento.documentId)
            .set(equipamento)
            .await()
    }

    suspend fun apagarTodosEquipamentos() {

        val snapshot = equipamentos.get().await()

        snapshot.documents.forEach {

            it.reference.delete().await()

        }
    }
}