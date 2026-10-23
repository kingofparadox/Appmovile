package com.example.myapplication.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BarraTexto(
    textoA: String,
    onText:(String)-> Unit,
    leyenda: String = "escribe aqui"

){
    OutlinedTextField(
        value = textoA,
        {
            nuevoTexto->
            onText(nuevoTexto)
        },
        Modifier.fillMaxWidth()
            .padding(16.dp,8.dp),
        shape = RoundedCornerShape(12.dp),
        placeholder = { Text(text = leyenda) },
        singleLine = true
    )
}