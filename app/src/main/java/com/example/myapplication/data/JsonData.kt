package com.example.myapplication.data

import com.example.myapplication.dtos.Asignatura
import com.example.myapplication.dtos.Chats
import com.example.myapplication.dtos.Contenido
import com.example.myapplication.dtos.ContenidoAsignatura
import com.example.myapplication.dtos.Credencial
import com.example.myapplication.dtos.Mensaje
import com.example.myapplication.dtos.User

object JsonData {
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
    val baseDatosMock = listOf(
        Credencial(
            rut = "12.345.678-9",
            password = "1234",
            user = User(
                Nombre = "Carlos",
                Apellido = "González",
                Rut = "12.345.678-9",
                rol = "Estudiante"
            )
        ),
        Credencial(
            rut = "9.876.543-2",
            password = "admin2026",
            user = User(
                Nombre = "Marta",
                Apellido = "Silva",
                Rut = "9.876.543-2",
                rol = "Instructora"
            )
        )
    )



}