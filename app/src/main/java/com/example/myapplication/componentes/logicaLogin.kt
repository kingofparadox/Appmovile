package com.example.myapplication.componentes

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.bdProvicional.AppDatabase
import com.example.myapplication.dtos.User
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class logicaLogin(application: Application) : AndroidViewModel(application) {

    var rutInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    var mensajeError by mutableStateOf<String?>(null)
        private set

    var cargando by mutableStateOf(false)
        private set

    fun actualizarRut(nuevoRut: String) {
        rutInput = nuevoRut
        mensajeError = null
    }

    fun actualizarPassword(nuevaPassword: String) {
        passwordInput = nuevaPassword
        mensajeError = null
    }

    fun intentarLogin(onSucces: (User) -> Unit) {
        if (cargando) return

        if (rutInput.isBlank() || passwordInput.isBlank()) {
            mensajeError = "por favor completa los campos "
            return
        }

        viewModelScope.launch {
            cargando = true
            try {
                val userDao = AppDatabase.getDatabase(getApplication()).userDao()
                val usuarioEncontrado = userDao.autenticarUsuario(rutInput.trim(), passwordInput)
                if (usuarioEncontrado != null) {
                    mensajeError = null
                    onSucces(usuarioEncontrado)
                } else {
                    mensajeError = "RUT o contraseña incorrectos"
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                mensajeError = "eroor al intentar inicar secion"
            } finally {
                cargando = false
            }
        }
    }
}