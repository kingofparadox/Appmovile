package com.example.myapplication.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.dtos.Asignatura

@Composable
 fun  card(asignaturas: Asignatura){

        Card(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)

        ) {
            Column(
                Modifier.fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(asignaturas.Id,
                    textAlign = TextAlign.Center)
                Text(asignaturas.Nombre,
                    textAlign = TextAlign.Center)

            }

        }

 }