package com.example.myapplication.pages

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.componentes.Rutas
import com.example.myapplication.componentes.nanbar
import androidx.navigation.compose.NavHost
import com.example.myapplication.componentes.logicaLogin
import com.example.myapplication.dtos.Asignatura
import com.example.myapplication.dtos.Chats
import  com.example.myapplication.data.JsonData
import com.example.myapplication.dtos.User
import java.security.GuardedObject

@Composable
fun Main(){
    val logVista: logicaLogin = viewModel()
    var usuarioLogeado by remember { mutableStateOf<User?>(null) }
    if(usuarioLogeado==null){
        VistaLog (logVista,
            {
                usuario->
                usuarioLogeado =usuario
            })
    }else{
        inicio(usuarioLogeado!!){
            usuarioLogeado = null
        }
    }
}

@Composable
fun inicio(usuario: User,onLogut:()-> Unit){
    val navController= rememberNavController()
    Scaffold(
        bottomBar = { nanbar(navController=navController) }
    ) { innerPading->
        NavHost(
            navController=navController,
            startDestination = Rutas.Asignaturas.route,
            modifier= Modifier.padding(innerPading)
        ){
            composable(Rutas.Asignaturas.route){
                AsignaturasRu(JsonData.listaAsignaturasEjemplo)

            }
            composable(Rutas.Perfil.route){
               PerfilVita(
                   usuario,
                   onLogut,
                   {upadaTeUser->}
               )
            }
            composable(Rutas.Mensajes.route){
                MensajesRu(JsonData.listaChats)
            }
        }
    }
}



@Composable
fun ConfiguracionScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Vista de Configuración",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun MensajesRu(listtaChat:List<Chats>){
    var chatsSeleccionado by remember  { mutableStateOf<Chats?>(null) }
    if (chatsSeleccionado==null ){mensajes(
        listtaChat,
        {chatPulsado-> chatsSeleccionado = chatPulsado}

    ) } else {
        DetalleMensaje(chatsSeleccionado!!,
            {chatsSeleccionado= null})
    }

}
@Composable
fun AsignaturasRu(listaAsignaturas: List<Asignatura>) {
    // Estado para saber qué asignatura está seleccionada (nulo al inicio)
    var asignaturaSeleccionada by remember { mutableStateOf<Asignatura?>(null) }

    if (asignaturaSeleccionada == null) {
        // Muestra la lista general de asignaturas (corregido el nombre a HomeAsignatura)
        HoemAsigntura (
            listaAsignaturas,
            { asignaturaPulsada ->
                asignaturaSeleccionada = asignaturaPulsada
            }
        )
    } else {
        // Muestra el detalle de la asignatura seleccionada usando 'onVolver'
        DetalleAsignaturaScreen (
            asignaturaSeleccionada!!,
             asignaturaSeleccionada!!.contendio
            ,{
                asignaturaSeleccionada = null // Esto limpia el estado y regresa a la lista
            }
        )
    }
}
