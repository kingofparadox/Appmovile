package com.example.myapplication.bdProvicional

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.dtos.User

@Dao
interface UserRepo {

    @Query("""
        SELECT u.nombre AS Nombre, 
               u.rut AS Rut, 
               u.apellido AS Apellido, 
               r.nombre AS rol 
        FROM usuarios u 
        INNER JOIN roles r ON u.rolId = r.id 
        WHERE u.rut = :rutInput AND u.password = :passwordInput 
        LIMIT 1
    """)
    suspend fun autenticarUsuario(rutInput: String, passwordInput: String): User?

}