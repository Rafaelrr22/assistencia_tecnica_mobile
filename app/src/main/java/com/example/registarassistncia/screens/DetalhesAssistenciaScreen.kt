package com.example.registarassistncia.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Handyman
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.AlertDialog
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.widget.Toast
import android.widget.Toast.makeText
import java.io.File
import java.io.FileOutputStream
import kotlinx.coroutines.launch
import com.example.registarassistncia.data.database.DatabaseProvider
import com.example.registarassistncia.data.entity.AssistenciaEntity
import com.example.registarassistncia.data.entity.ClienteEntity
import com.example.registarassistncia.data.entity.EquipamentoEntity

@Composable
fun DetalhesAssistenciaScreen(
    modifier: Modifier = Modifier,
    assistenciaId: Int,
    onBackClick: () -> Unit,
    onEditarClick: (Int) -> Unit
) {


    //VARIÁVEIS
    val context = LocalContext.current

    var assistencia by remember {
        mutableStateOf<AssistenciaEntity?>(null)
    }

    var cliente by remember {
        mutableStateOf<ClienteEntity?>(null)
    }

    var equipamento by remember {
        mutableStateOf<EquipamentoEntity?>(null)
    }

    val scope = rememberCoroutineScope()

    var mostrarDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(assistenciaId) {

        val db = DatabaseProvider.getDatabase(context)

        assistencia =
            db.assistenciaDao()
                .obterPorId(assistenciaId)

        assistencia?.let {

            cliente =
                db.clienteDao()
                    .obterPorId(it.clienteId)

            equipamento =
                db.equipamentoDao()
                    .obterPorId(it.equipamentoId)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Detalhes Assistência",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE3F2FD)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                // CAMPO CLIENTE
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Cliente")
                }

                Text(cliente?.nome ?: "")

                Spacer(modifier = Modifier.height(8.dp))

                // CAMPO EQUIPAMENTO
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Equipamento")
                }

                Text(
                    "${equipamento?.marca ?: ""} ${equipamento?.modelo ?: ""}"
                )

                Spacer(modifier = Modifier.height(8.dp))

                // CAMPO DATA
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Data: 08/06/2026")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO PROBLEMA
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Problema")
                }

                Text(assistencia?.problema ?: "")

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO DIAGNÓSTICO
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Diagnóstico")
                }

                Text(assistencia?.diagnostico ?: "")

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO SOLUÇÃO
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Handyman,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Solução")
                }

                Text(assistencia?.solucao ?: "")

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO ESTADO
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Estado")
                }

                Text(
                    text = assistencia?.estado ?: "",
                    color = Color(0xFFFF9800)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO ORÇAMENTO
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Orçamento")
                }

                Text("${assistencia?.orcamento ?: 0.0} €")

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO TEMPO GASTO
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Tempo gasto")
                }

                Text("45 min")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // BOTÃO EDITAR
        Button(
            onClick = {

                assistencia?.let {

                    onEditarClick(it.id)
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Editar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // BOTÃO APAGAR
        Button(
            onClick = { mostrarDialog = true},
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Apagar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // BOTÃO PDF
        Button(
                onClick = {

                    gerarPdfAssistencia(
                        context = context,
                        cliente = cliente?.nome ?: "",
                        equipamento =
                            "${equipamento?.marca ?: ""} ${equipamento?.modelo ?: ""}",
                        problema = assistencia?.problema ?: "",
                        diagnostico = assistencia?.diagnostico ?: "",
                        solucao = assistencia?.solucao ?: "",
                        estado = assistencia?.estado ?: "",
                        orcamento = assistencia?.orcamento ?: 0.0
                    )
                },

            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Icon(
                imageVector = Icons.Default.PictureAsPdf,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("PDF")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // BOTÃO VOLTAR
        Button(
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text("Voltar")
        }
    }

    if (mostrarDialog) {

        AlertDialog(
            onDismissRequest = {
                mostrarDialog = false
            },
            title = {
                Text("Apagar Assistência")
            },
            text = {
                Text(
                    "Tem a certeza que pretende apagar esta assistência?"
                )
            },
            confirmButton = {

                Button(
                    onClick = {

                        mostrarDialog = false

                        scope.launch {

                            assistencia?.let {

                                val db =
                                    DatabaseProvider.getDatabase(context)

                                db.assistenciaDao()
                                    .apagar(it)

                                onBackClick()
                            }
                        }
                    }
                ) {
                    Text("Apagar")
                }
            },
            dismissButton = {

                Button(
                    onClick = {
                        mostrarDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

}

    //PDF
    fun gerarPdfAssistencia(
        context: Context,
        cliente: String,
        equipamento: String,
        problema: String,
        diagnostico: String,
        solucao: String,
        estado: String,
        orcamento: Double
    ) {

        val pdfDocument = PdfDocument()

        val pageInfo = PdfDocument.PageInfo.Builder(
            595,
            842,
            1
        ).create()

        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas

        val paint = Paint()

        paint.textSize = 18f

        canvas.drawText(
            "Assistência Técnica",
            40f,
            50f,
            paint
        )

        paint.textSize = 14f

        canvas.drawText(
            "Cliente: $cliente",
            40f,
            100f,
            paint
        )

        canvas.drawText(
            "Equipamento: $equipamento",
            40f,
            130f,
            paint
        )

        canvas.drawText(
            "Problema: $problema",
            40f,
            160f,
            paint
        )

        canvas.drawText(
            "Diagnóstico: $diagnostico",
            40f,
            190f,
            paint
        )

        canvas.drawText(
            "Solução: $solucao",
            40f,
            220f,
            paint
        )

        canvas.drawText(
            "Estado: $estado",
            40f,
            250f,
            paint
        )

        canvas.drawText(
            "Orçamento: €$orcamento",
            40f,
            280f,
            paint
        )

        pdfDocument.finishPage(page)

        val file = File(
            context.getExternalFilesDir(null),
            "assistencia.pdf"
        )

        pdfDocument.writeTo(
            FileOutputStream(file)
        )

        pdfDocument.close()

        makeText(
            context,
            "PDF criado: ${file.name}",
            Toast.LENGTH_LONG
        ).show()
    }
