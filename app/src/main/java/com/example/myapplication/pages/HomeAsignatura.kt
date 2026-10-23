package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import  androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.componentes.cardAsignatura
import com.example.myapplication.dtos.Asignatura

@Composable
fun HoemAsigntura(asignaturas: List<Asignatura>, onAsignatura:(Asignatura)-> Unit){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(242,242,242)) // Fondo gris suave
            , Alignment.Center
    ) {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(40.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          items(asignaturas){
              asignatura->
              cardAsignatura (asignatura,
                  {onAsignatura(asignatura)})
          }
        }
    }
}