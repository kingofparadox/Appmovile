package com.example.myapplication.componentes

import android.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.dtos.Contenido

@Composable
fun contenidoRender(item: Contenido )
{
    Card(
        modifier = Modifier.fillMaxWidth().
        padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(242,242,242)),
        shape = RoundedCornerShape(6.dp)
    ) {
        when(item.tipo){
            "LINK"->{
                Text(
                    "enlase: ${item.contenido}",
                    color = Color(0,0,242),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
            "TEXTO"->{
                Text(
                    item.contenido,
                    color = Color(51,51,51),
                    fontSize = 13.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }else->{
                Text(
                    text = item.contenido,
                    color = Color.DarkGray,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}