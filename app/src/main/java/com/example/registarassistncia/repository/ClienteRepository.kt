package com.example.registarassistncia.repository

import com.example.registarassistncia.data.entity.ClienteEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ClienteRepository {

    private val firestore = FirebaseFirestore.getInstance()

    private val clientes =
        firestore.collection("clientes")

    suspend fun adicionarCliente(cliente: ClienteEntity): Boolean {

        // Verifica se já existe um cliente com o mesmo NIF
        val existe = clientes
            .whereEqualTo("nif", cliente.nif)
            .get()
            .await()

        if (!existe.isEmpty) {
            return false
        }

        val docRef = clientes.document()

        cliente.documentId = docRef.id

        docRef
            .set(cliente)
            .await()

        return true
    }

    suspend fun obterClientes(): List<ClienteEntity> {

        val snapshot = clientes
            .get()
            .await()

        return snapshot.documents.mapNotNull { document ->

            document.toObject(ClienteEntity::class.java)?.apply {

                documentId = document.id

            }
        }
    }

    suspend fun obterCliente(documentId: String): ClienteEntity? {

        return clientes
            .document(documentId)
            .get()
            .await()
            .toObject(ClienteEntity::class.java)
            ?.apply {

                this.documentId = documentId

            }
    }

    suspend fun atualizarCliente(cliente: ClienteEntity) {

        clientes
            .document(cliente.documentId)
            .set(cliente)
            .await()
    }

    suspend fun apagarCliente(documentId: String) {

        clientes
            .document(documentId)
            .delete()
            .await()
    }
}