package com.example.registarassistncia.repository

import com.example.registarassistncia.data.entity.AssistenciaEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AssistenciaRepository {

    private val firestore = FirebaseFirestore.getInstance()

    private val assistencias =
        firestore.collection("assistencias")

    suspend fun adicionarAssistencia(
        assistencia: AssistenciaEntity
    ): Boolean {

        val docRef = assistencias.document()

        assistencia.documentId = docRef.id

        docRef.set(assistencia).await()

        return true
    }

    suspend fun obterAssistencias(): List<AssistenciaEntity> {

        val snapshot = assistencias.get().await()

        return snapshot.documents.mapNotNull { document ->

            document.toObject(
                AssistenciaEntity::class.java
            )?.apply {

                documentId = document.id
            }
        }
    }

    suspend fun obterAssistencia(
        documentId: String
    ): AssistenciaEntity? {

        return assistencias
            .document(documentId)
            .get()
            .await()
            .toObject(
                AssistenciaEntity::class.java
            )
            ?.apply {

                this.documentId = documentId
            }
    }

    suspend fun atualizarAssistencia(
        assistencia: AssistenciaEntity
    ) {

        assistencias
            .document(assistencia.documentId)
            .set(assistencia)
            .await()
    }

    suspend fun apagarAssistencia(
        documentId: String
    ) {

        assistencias
            .document(documentId)
            .delete()
            .await()
    }
}