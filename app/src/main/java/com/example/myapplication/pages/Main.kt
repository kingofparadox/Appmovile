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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.componentes.Rutas
import com.example.myapplication.componentes.nanbar
import androidx.navigation.compose.NavHost

import com.example.myapplication.componentes.contenidoRender
import com.example.myapplication.dtos.Asignatura
import com.example.myapplication.dtos.Chats
import com.example.myapplication.dtos.Contenido
import com.example.myapplication.dtos.ContenidoAsignatura
import com.example.myapplication.dtos.Mensaje
import com.example.myapplication.dtos.User

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
                AsignaturasRu(listaAsignaturasEjemplo)

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

val listaAsignaturasEjemplo = mutableListOf(
    Asignatura(
        Id = "ASIG-101",
        Nombre = "Derecho Penal y Procesal",
        Profesor = mutableListOf(
            User(
                Nombre = "Carlos",
                Apellido = "González",
                Rut = "12.345.678-9",
                rol = "Docente Principal"
            )
        ),
        contendio = ContenidoAsignatura(
            id = "ASIG-101",
            contenidos = listOf(
                Contenido(
                    tipo = "texto",
                    contenido = "Bienvenidos a la asignatura. Revisen el material de lectura obligatoria antes del primer certamen."
                ),
                Contenido(
                    tipo = "link",
                    contenido = "https://esucar.cl/material/codigo-penal-actualizado.pdf"
                ),
                Contenido(
                    tipo = "link",
                    contenido = "https://esucar.cl/casos/guia-procedimientos-policiales.docx"
                )
            )
        )
    ),
    Asignatura(
        Id = "ASIG-102",
        Nombre = "Tácticas de Policial Operativa",
        Profesor = mutableListOf(
            User(
                Nombre = "Marta",
                Apellido = "Silva",
                Rut = "9.876.543-2",
                rol = "Instructora"
            ),
            User(
                Nombre = "Roberto",
                Apellido = "Morales",
                Rut = "15.432.109-8",
                rol = "Docente Ayudante"
            )
        ),
        contendio = ContenidoAsignatura(
            id = "ASIG-102",
            contenidos = listOf(
                Contenido(
                    tipo = "texto",
                    contenido = "Recordatorio: La evaluación práctica en terreno se realizará el próximo viernes a las 08:30 hrs."
                ),
                Contenido(
                    tipo = "link",
                    contenido = "https://esucar.cl/videos/protocolo-intervencion-urbana.mp4"
                )
            )
        )
    )
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
    var chatsSeleccionado by remember  { mutableStateOf<Chats?>(null) }
    if (chatsSeleccionado==null ){mensajes(
        listaChats,
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