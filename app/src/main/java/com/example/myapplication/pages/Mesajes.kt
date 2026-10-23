package com.example.myapplication.pages

import android.R
import android.text.Layout
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.componentes.BarraTexto
import com.example.myapplication.dtos.Chats

@Composable
fun DetalleMensaje(
    chat: Chats,
    onVolver: () -> Unit
) {

    var textoRespuesta by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(242, 242, 242))
            .padding(16.dp)
    ) {
        Button(
            onClick = onVolver,
            colors = ButtonDefaults.buttonColors(contentColor = Color(50, 114, 54, 255)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "<- volver", color = Color.White)
        }

        Spacer(Modifier.height(16.dp))

        Card(
            Modifier
                .fillMaxWidth()
                .weight(1f), // Hace que la tarjeta ocupe el espacio superior y deje lugar abajo para la barra
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    Arrangement.SpaceBetween,
                    Alignment.CenterVertically
                ) {
                    Text(
                        text = chat.Asignatura,
                        color = Color(27, 94, 32),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                    Text(
                        text = chat.hora,
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "De: ${chat.remitente}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
                HorizontalDivider(
                    Modifier.padding(vertical = 12.dp),
                    thickness = 1.dp,
                    color = Color(224, 224, 224)
                )
                Text(
                    text = chat.mensaje.titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(Modifier.height(12.dp))

                Text(
                    text = chat.mensaje.cuerpo,
                    fontSize = 14.sp,
                    color = Color(51, 51, 51),
                    lineHeight = 20.sp
                )

                // CORREGIDO: Usamos isNotEmpty() para que se muestren solo si hay elementos
                if (chat.mensaje.links.isNotEmpty()) {
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = "archivos / enlaces",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    chat.mensaje.links.forEach { link ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = link,
                                color = Color(25, 118, 210),
                                fontSize = 13.sp,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            Modifier.fillMaxWidth(),
            Arrangement.spacedBy(  8.dp ),
            Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f)){
                BarraTexto(
                    textoRespuesta,
                    { nuevoTexto ->
                        textoRespuesta = nuevoTexto
                    },
                    "Escribe una respuesta..."
                )
            }

            Button(
                {
                    if(textoRespuesta.isNotBlank()){
                        println("enviadno mensaje ${textoRespuesta}")
                        textoRespuesta=""
                    }
                },
                colors=ButtonDefaults.buttonColors(Color(27,94,32)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("enviar", color = Color.White)
            }
        }

    }
}