package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.componentes.cardAsignatura
import com.example.myapplication.dtos.Asignatura
import com.example.myapplication.dtos.ContenidoAsignatura
import com.example.myapplication.dtos.User

@Composable
fun HoemAsigntura(
    asignaturasIniciales: List<Asignatura>,
    usuarioLogueado: User,
    onAsignatura: (Asignatura) -> Unit
) {
    val puedeGestionar = usuarioLogueado.rol.contains("Profesor", ignoreCase = true) ||
            usuarioLogueado.rol.contains("Docente", ignoreCase = true) ||
            usuarioLogueado.rol.contains("Admin", ignoreCase = true)


    val listaAsignaturas = remember {
        mutableStateListOf<Asignatura>().apply {
            addAll(asignaturasIniciales)
        }
    }

    var mostrarDialogo by remember { mutableStateOf(false) }
    var idInput by remember { mutableStateOf("") }
    var nombreInput by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(242, 242, 242))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Asignaturas Disponibles",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )

                if (puedeGestionar) {
                    Button(
                        onClick = {
                            idInput = ""
                            nombreInput = ""
                            mostrarDialogo = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("+ Agregar", fontSize = 12.sp, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (listaAsignaturas.isEmpty()) {
                        item {
                            Text(
                                text = "No hay asignaturas registradas.",
                                fontSize = 13.sp,
                                color = Color.Gray,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    } else {
                        itemsIndexed(listaAsignaturas) { index, asignatura ->

                            cardAsignatura(
                                asignaturas = asignatura,
                                onClik = { onAsignatura(asignatura) }
                            )
                        }
                    }
                }
            }
        }
    }

    // 3. Diálogo flotante simple para agregar una nueva asignatura (si es Docente/Admin)
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = { mostrarDialogo = false },
            title = { Text(text = "Crear Nueva Asignatura", fontWeight = FontWeight.Bold) },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = idInput,
                        onValueChange = { idInput = it },
                        label = { Text("ID (ej: ASIG-105)") },
                        singleLine = true
                    )
                    OutlinedTextField(
                        value = nombreInput,
                        onValueChange = { nombreInput = it },
                        label = { Text("Nombre de la Asignatura") },
                        singleLine = true
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (idInput.isNotBlank() && nombreInput.isNotBlank()) {
                            val nueva = Asignatura(
                                Id = idInput,
                                Nombre = nombreInput,
                                Profesor = mutableListOf(),
                                contendio = ContenidoAsignatura(id = idInput, contenidos = mutableListOf())
                            )
                            listaAsignaturas.add(nueva)
                            mostrarDialogo = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
                ) {
                    Text("Guardar", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDialogo = false }) {
                    Text("Cancelar", color = Color.Gray)
                }
            }
        )
    }
}