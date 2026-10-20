package com.example.myapplication.dtos


data class  User( val Nombre: String,
                  val Rut: String,
                  val Apellido:String,
                  val rol: String)

data class  Asignatura(val Id: String,
                       val Nombre: String,
                       var Profesor: MutableList<User>)

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