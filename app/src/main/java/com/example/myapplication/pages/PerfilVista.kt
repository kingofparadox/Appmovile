package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.componentes.PerfilEdit
import com.example.myapplication.dtos.User

@Composable
fun  PerfilVita(
    user: User,
    LogOut:()-> Unit,
    Save:(User)-> Unit
){
    var nombreEdit by remember { mutableStateOf(user.Nombre) }
    var apellidoEdit by remember { mutableStateOf(user.Apellido) }
    val rutUser = user.Rut
    val rolUser = user.rol
    var isEditing by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red = 242, green = 242, blue = 242)),
        contentAlignment = Alignment.Center
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Mi Perfil",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    TextButton (onClick = {
                        if (isEditing) {
                            // Si estaba editando y presiona guardar, podríamos disparar la acción
                            val updatedUser = user.copy(
                                Nombre = nombreEdit,
                                Apellido = apellidoEdit,

                            )
                            Save(updatedUser)
                        }
                        isEditing = !isEditing
                    }) {
                        Text(text = if (isEditing) "Guardar" else "Editar")
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

                PerfilEdit (label = "Nombre", value = nombreEdit, edotor = isEditing) { nombreEdit = it }
                PerfilEdit (label = "Apellido", value = apellidoEdit, edotor = isEditing) { apellidoEdit = it }
                PerfilStaticItem(label = "RUT", value = rutUser)
                PerfilStaticItem(label = "Rol", value = rolUser)
                Spacer(modifier = Modifier.height(16.dp))

                Button (
                    onClick = LogOut,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text(
                        text = "Cerrar Sesión",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
@Composable
fun PerfilStaticItem(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.DarkGray // Un tono un poco más sobrio para denotar que no es modificable
        )
    }
}