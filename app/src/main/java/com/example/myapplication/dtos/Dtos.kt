package com.example.myapplication.dtos


data class  User( val Nombre: String,
                  val Rut: String,
                  val Apellido:String,
                  val rol: String)

data class  Asignatura(val Id: String,
                       val Nombre: String,
                       var Profesor: MutableList<User>,
                       val contendio: ContenidoAsignatura)


data class  Contenido(
    val tipo: String,
    val contenido: String
)

data class ContenidoAsignatura(
    val id: String,
    val contenidos: List<Contenido>
)

data class  Chats(val remitente: String,
                  val Asignatura: String,
                  val mensaje : Mensaje,
                  val hora:String,
                  val Receptor: String
    )

data class Mensaje(
    val titulo: String,
    val cuerpo: String,
    val links: MutableList<String> = mutableListOf()
)
