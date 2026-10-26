package com.example.myapplication.pages


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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


@Composable
fun Main(){
    val logVista: logicaLogin = viewModel()
    var usuarioLogeado by rememberSaveable { mutableStateOf<User?>(null) }
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
                AsignaturasRu(JsonData.listaAsignaturasEjemplo,usuario)

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
fun AsignaturasRu(
    listaAsignaturas: List<Asignatura>,
    usuarioLogueado: User // <-- Recibimos el usuario autenticado
) {
    // Estado para saber qué asignatura está seleccionada (nulo al inicio)
    var asignaturaSeleccionada by remember { mutableStateOf<Asignatura?>(null) }

    if (asignaturaSeleccionada == null) {
        // Muestra la lista general de asignaturas
        HoemAsigntura(
            asignaturas = listaAsignaturas,
            onAsignatura = { asignaturaPulsada ->
                asignaturaSeleccionada = asignaturaPulsada
            }
        )
    } else {
        // Muestra el detalle de la asignatura seleccionada
        DetalleAsignaturaScreen(
            asignatura = asignaturaSeleccionada!!,
            contenidoAsignatura = asignaturaSeleccionada!!.contendio,
            usuarioLogueado = usuarioLogueado, // <-- Se lo pasamos aquí
            onVolver = {
                asignaturaSeleccionada = null // Limpia el estado y regresa a la lista
            }
        )
    }
}
