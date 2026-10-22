package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.componentes.contenidoRender
import com.example.myapplication.dtos.Asignatura
import com.example.myapplication.dtos.ContenidoAsignatura

@Composable
fun DetalleAsignaturaScreen(
    asignatura: Asignatura,
    contenidoAsignatura: ContenidoAsignatura?, // Recibe su contenido asociado por ID
    onVolver: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(242,242,242))
            .padding(16.dp)
    ) {
        Button (
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "← Volver a Asignaturas", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card (
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
                Text(
                    text = "ID: ${asignatura.Id}",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = asignatura.Nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                // --- SECCIÓN DE CONTENIDO DINÁMICO ---
                Text(
                    text = "Contenido de la Asignatura:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF1B5E20)
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (contenidoAsignatura == null || contenidoAsignatura.contenidos.isEmpty()) {
                    Text(
                        text = "No hay contenido disponible para esta asignatura.",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                } else {
                    // Recorremos la lista flexible y delegamos el renderizado al componente
                    contenidoAsignatura.contenidos.forEach { item ->
                        contenidoRender(item = item)
                    }
                }
            }
        }
    }
}