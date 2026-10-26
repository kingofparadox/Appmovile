package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.componentes.logicaLogin
import com.example.myapplication.dtos.User


@Composable
fun VistaLog(
    vistaLogin: logicaLogin,
    onLogin: (User) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(242, 242, 242)),
        Alignment.Center
    ) {
        Card (
            Modifier
                .fillMaxWidth(0.88f)
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column (
                Modifier.fillMaxWidth().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Acceso Institucional",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(27, 94, 32)
                )
                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Ingrese su RUT y contraseña para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = vistaLogin.rutInput,
                    onValueChange = { vistaLogin.actualizarRut(it) },
                    label = { Text("Rut( ej: 12.222.223-7)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(Modifier.height(22.dp))

                OutlinedTextField(
                    value = vistaLogin.passwordInput,
                    onValueChange = { vistaLogin.actualizarPassword(it) },
                    label = { Text("Contraseña") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                if (vistaLogin.mensajeError != null) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        vistaLogin.mensajeError!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = { vistaLogin.intentarLogin { onLogin(it) } },
                    enabled = !vistaLogin.cargando,
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(27, 94, 32)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        "Iniciar Sesion",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}