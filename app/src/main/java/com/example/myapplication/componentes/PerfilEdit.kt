package com.example.myapplication.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PerfilEdit(
    label: String,
    value: String,
    edotor: Boolean,
    modElemento:(String)-> Unit
){
    Row(
        Modifier.fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Text(
            label,
            style =  MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.alignByBaseline()
        )
        if(edotor){
            OutlinedTextField(
                value =value,
                onValueChange = modElemento,
                Modifier.width(180.dp).height(50.dp),
                textStyle = MaterialTheme.typography.bodyLarge

            )
        }else{
            Text(
                value,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier.alignByBaseline()
            )
        }
    }
}