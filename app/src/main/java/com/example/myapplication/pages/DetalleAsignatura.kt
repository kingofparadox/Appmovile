package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.componentes.contenidoRender
import com.example.myapplication.dtos.Asignatura
import com.example.myapplication.dtos.Contenido
import com.example.myapplication.dtos.ContenidoAsignatura
import com.example.myapplication.dtos.User



@Composable
fun DetalleAsignaturaScreen(
    asignatura: Asignatura,
    contenidoAsignatura: ContenidoAsignatura?,
    usuarioLogueado: User,
    onVolver: () -> Unit
) {
    // 1. Control de permisos por rol
    val puedeGestionarContenido = usuarioLogueado.rol.contains("Profesor", ignoreCase = true) ||
            usuarioLogueado.rol.contains("Admin", ignoreCase = true)

    // 2. Lista de contenidos reactiva en memoria volátil
    val listaContenidos = remember(contenidoAsignatura?.id) {
        mutableStateListOf<Contenido>().apply {
            contenidoAsignatura?.contenidos?.let { addAll(it) }
        }
    }

    // 3. Conjunto reactivo para registrar qué enlaces (LINK) han sido marcados como revisados
    val elementosRevisados = remember(contenidoAsignatura?.id) {
        mutableStateSetOf<Int>()
    }

    // 4. Cálculo del porcentaje de progreso (solo se evalúan elementos tipo LINK)
    val totalLinks = listaContenidos.count { it.tipo.equals("LINK", ignoreCase = true) }
    val linksRevisados = elementosRevisados.count { index ->
        index < listaContenidos.size && listaContenidos[index].tipo.equals("LINK", ignoreCase = true)
    }
    val progreso = if (totalLinks > 0) linksRevisados.toFloat() / totalLinks else 0f

    // 5. Estados para modal de agregar/editar
    var mostrarDialogo by remember { mutableStateOf(false) }
    var contenidoEnEdicion by remember { mutableStateOf<Contenido?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(242, 242, 242))
            .padding(16.dp)
    ) {
        // Botón Volver
        Button(
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "← Volver a Asignaturas", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header Asignatura
                Text(
                    text = "ID: ${asignatura.Id}",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = asignatura.Nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // BARRA DE PROGRESO DE AUTOAPRENDIZAJE
                if (totalLinks > 0) {
                    Text(
                        text = "Progreso de lectura/revisión: ${(progreso * 100).toInt()}%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { progreso },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = Color(0xFF1B5E20),
                        trackColor = Color(0xFFE0E0E0),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))

                // Título y botón para agregar material
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Contenido de la Asignatura:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF1B5E20)
                    )

                    if (puedeGestionarContenido) {
                        Button(
                            onClick = {
                                contenidoEnEdicion = null
                                mostrarDialogo = true
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text("+ Agregar", fontSize = 12.sp, color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // LISTA DE CONTENIDOS
                if (listaContenidos.isEmpty()) {
                    Text(
                        text = "No hay contenido disponible para esta asignatura.",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                } else {
                    listaContenidos.forEachIndexed { index, item ->
                        val esLink = item.tipo.equals("LINK", ignoreCase = true)
                        val estaRevisado = elementosRevisados.contains(index)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .then(
                                    if (esLink) {
                                        Modifier.clickable {
                                            if (estaRevisado) {
                                                elementosRevisados.remove(index)
                                            } else {
                                                elementosRevisados.add(index)
                                            }
                                        }
                                    } else {
                                        Modifier
                                    }
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (esLink && estaRevisado) Color(0xFFE8F5E9) else Color(242, 242, 242)
                            ),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Column(modifier = Modifier.padding(4.dp)) {
                                contenidoRender(item = item)

                                // Solo los enlaces muestran la etiqueta interactiva de progreso
                                if (esLink) {
                                    Text(
                                        text = if (estaRevisado) "✔ Material revisado" else "○ Toca para marcar como revisado",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (estaRevisado) Color(0xFF2E7D32) else Color.Gray,
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                                    )
                                }

                                // Botones de edición/eliminación para docentes
                                if (puedeGestionarContenido) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 8.dp),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        TextButton(onClick = {
                                            contenidoEnEdicion = item
                                            mostrarDialogo = true
                                        }) {
                                            Text("Editar", fontSize = 11.sp, color = Color(0xFF1B5E20))
                                        }
                                        TextButton(onClick = {
                                            listaContenidos.removeAt(index)
                                            elementosRevisados.remove(index)
                                        }) {
                                            Text("Eliminar", fontSize = 11.sp, color = Color.Red)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal Formulario para Crear / Editar
    if (mostrarDialogo) {
        DialogoFormularioContenido(
            contenidoInicial = contenidoEnEdicion,
            onDismiss = { mostrarDialogo = false },
            onGuardar = { tipo, texto ->
                if (contenidoEnEdicion == null) {
                    listaContenidos.add(Contenido(tipo = tipo, contenido = texto))
                } else {
                    val idx = listaContenidos.indexOf(contenidoEnEdicion)
                    if (idx != -1) {
                        listaContenidos[idx] = Contenido(tipo = tipo, contenido = texto)
                    }
                }
                mostrarDialogo = false
            }
        )
    }
}