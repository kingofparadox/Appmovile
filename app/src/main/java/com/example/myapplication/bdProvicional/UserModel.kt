package com.example.myapplication.bdProvicional

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    "usuarios",
    foreignKeys = [
        ForeignKey(
            entity = RolModel::class,
            ["id"],
            ["rolId"],
            ForeignKey.CASCADE
        )
    ]
)

data class UserModel(
    @PrimaryKey val rut: String,
    val nombre: String,
    val apellido: String,
    val password: String, // Contraseña para validar el login offline
    val rolId: String,
) {
}