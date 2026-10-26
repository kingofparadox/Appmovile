package com.example.myapplication.componentes

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.dtos.Asignatura

class CudAsignatura : ViewModel() {
    // Lista reactiva inicial en memoria
    val listaAsignaturas = mutableStateListOf<Asignatura>()

    // Función para agregar una nueva asignatura
    fun agregarAsignatura(nuevaAsignatura: Asignatura) {
        listaAsignaturas.add(nuevaAsignatura)
    }

    // Función para eliminar
    fun eliminarAsignatura(asignatura: Asignatura) {
        listaAsignaturas.remove(asignatura)
    }
}