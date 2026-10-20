package com.example.myapplication.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.componentes.Rutas
import com.example.myapplication.componentes.nanbar
import androidx.navigation.compose.NavHost
import com.example.myapplication.dtos.Asignatura
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
               HoemAsigntura(listaAsignaturas)

            }
            composable(Rutas.Perfil.route){
                ConfiguracionScreen()
            }
            composable(Rutas.Mensajes.route){
                MensajesScreen()
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

@Composable
fun MensajesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Vista de Mensajes / Consultas",
            style = MaterialTheme.typography.headlineMedium
        )
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