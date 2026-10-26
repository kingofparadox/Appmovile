package com.example.myapplication.bdProvicional


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("roles")
data class RolModel(
    @PrimaryKey
    val id: String,
    val nombre: String

) {
}