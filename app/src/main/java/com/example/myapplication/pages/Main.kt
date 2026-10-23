package com.example.myapplication.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.componentes.Rutas
import com.example.myapplication.componentes.nanbar
import androidx.navigation.compose.NavHost
import com.example.myapplication.dtos.Asignatura
import com.example.myapplication.dtos.Chats
import com.example.myapplication.dtos.Mensaje
import com.example.myapplication.dtos.User
import androidx.compose.runtime.setValue

@Composable
fun Main(){
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
               HoemAsigntura(listaAsignaturas)

            }
            composable(Rutas.Perfil.route){
                ConfiguracionScreen()
            }
            composable(Rutas.Mensajes.route){
                MensajesRu(listaChats)
            }
        }
    }
}

val listaAsignaturas = listOf(
    Asignatura(Id = "ASIG-01", Nombre = "Derecho Penal y Procesal Penal",
        mutableListOf(
            User(
                Nombre = "Carlos",
                Apellido = "González",
                Rut = "18.234.567-8",
                rol = "Prodfesor"
            ),
            User(
                Nombre = "María",
                Apellido = "Soto",
                Rut = "17.890.123-K",
                rol = "Profesor"
            )

        ) )
       ,
    Asignatura(Id = "ASIG-02", Nombre = "Doctrina y Ética Institucional",
        mutableListOf(
            User(
                Nombre = "Carlos",
                Apellido = "González",
                Rut = "18.234.567-8",
                rol = "Prodfesor"
            ),
            User(
                Nombre = "María",
                Apellido = "Soto",
                Rut = "17.890.123-K",
                rol = "Profesor"
            )

        )),
    Asignatura(Id = "ASIG-03", Nombre = "Técnicas de Intervención Policial",
        mutableListOf(
            User(
                Nombre = "Carlos",
                Apellido = "González",
                Rut = "18.234.567-8",
                rol = "Prodfesor"
            ),
            User(
                Nombre = "María",
                Apellido = "Soto",
                Rut = "17.890.123-K",
                rol = "Profesor"
            )

        )),
    Asignatura(Id = "ASIG-04", Nombre = "Legislación de Tránsito y Vialidad",
        mutableListOf(
            User(
                Nombre = "Carlos",
                Apellido = "González",
                Rut = "18.234.567-8",
                rol = "Prodfesor"
            ),
            User(
                Nombre = "María",
                Apellido = "Soto",
                Rut = "17.890.123-K",
                rol = "Profesor"
            )

        )),
    Asignatura(Id = "ASIG-05", Nombre = "Prevención y Orden Público",
        mutableListOf(
            User(
                Nombre = "Carlos",
                Apellido = "González",
                Rut = "18.234.567-8",
                rol = "Prodfesor"
            ),
            User(
                Nombre = "María",
                Apellido = "Soto",
                Rut = "17.890.123-K",
                rol = "Profesor"
            )

        ))
)

val listaChats: MutableList<Chats> = mutableListOf(
    Chats(
        remitente = "Sgto. 1° Carlos González",
        Asignatura = "Derecho Penal",
        mensaje = Mensaje(
            titulo = "Entrega de Caso Práctico",
            cuerpo = "Hola alumno, recuerde enviar la resolución del caso antes de las 18:00.",
            links = mutableListOf("https://esucar.cl/documentos/caso-penal-01.pdf")
        ),
        hora = "14:30",
        Receptor = "Alumno"
    ),
    Chats(
        remitente = "Cabo 2° María Soto",
        Asignatura = "Prevención y Orden",
        mensaje = Mensaje(
            titulo = "Pauta de Patrullaje",
            cuerpo = "Adjunto las directrices para la simulación táctica de mañana en el patio central.",
            links = mutableListOf("https://esucar.cl/pautas/patrullaje-2026.pdf")
        ),
        hora = "12:15",
        Receptor = "Alumno"
    ),
    Chats(
        remitente = "Capitán Alejandro Pérez",
        Asignatura = "Doctrina Institucional",
        mensaje = Mensaje(
            titulo = "Lectura Obligatoria",
            cuerpo = "Revisar los capítulos indicados en el anexo para el control de lectura de la próxima semana.",
            links = mutableListOf() // Lista vacía por ahora, lista para crecer
        ),
        hora = "Ayer",
        Receptor = "Alumno"
    )
)

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
    var chatsSeleccionado by remember { mutableStateOf<Chats?>(null) }
    if (chatsSeleccionado==null ){mensajes(
        listaChats,
        {chatPulsado-> chatsSeleccionado = chatPulsado}

    ) } else {
        DetalleMensaje(chatsSeleccionado!!,
            {chatsSeleccionado= null})
    }

}