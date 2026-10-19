package com.example.myapplication.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed  class Rutas(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Asignaturas: Rutas("asignaturas", "Asignaturas",Icons.Default.Folder)
    object Mensajes: Rutas("mensajes", "Mensajes",Icons.Default.Message)
    object Perfil: Rutas("perfil", "Perfil",Icons.Default.Settings)
}