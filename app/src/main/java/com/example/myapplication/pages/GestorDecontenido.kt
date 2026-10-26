package com.example.myapplication.pages

import com.example.myapplication.componentes.contenidoRender
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.dtos.Contenido


@Composable
fun ItemContenidoConAcciones(
    item: Contenido,
    puedeEditar: Boolean,
    onEditar: (Contenido) -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(242, 242, 242)),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // Render de contenido existente
            contenidoRender(item = item)

            // Acciones de edición y eliminación (solo si tiene rol permitido)
            if (puedeEditar) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onEditar(item) }) {
                        Text("Editar", fontSize = 12.sp, color = Color(0xFF1B5E20))
                    }
                    TextButton(onClick = onEliminar) {
                        Text("Eliminar", fontSize = 12.sp, color = Color.Red)
                    }
                }
            }
        }
    }
}

@Composable
fun DialogoFormularioContenido(
    contenidoInicial: Contenido? = null,
    onDismiss: () -> Unit,
    onGuardar: (String, String) -> Unit
) {
    var tipo by remember { mutableStateOf(contenidoInicial?.tipo ?: "TEXTO") }
    var textoContenido by remember { mutableStateOf(contenidoInicial?.contenido ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (contenidoInicial == null) "Agregar Contenido" else "Editar Contenido",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Tipo de Contenido:", fontSize = 12.sp, color = Color.Gray)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = tipo == "TEXTO",
                        onClick = { tipo = "TEXTO" },
                        label = { Text("TEXTO") }
                    )
                    FilterChip(
                        selected = tipo == "LINK",
                        onClick = { tipo = "LINK" },
                        label = { Text("LINK") }
                    )
                }
                OutlinedTextField(
                    value = textoContenido,
                    onValueChange = { textoContenido = it },
                    label = { Text("Contenido / Enlace") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (textoContenido.isNotBlank()) {
                        onGuardar(tipo, textoContenido)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
            ) {
                Text("Guardar", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}