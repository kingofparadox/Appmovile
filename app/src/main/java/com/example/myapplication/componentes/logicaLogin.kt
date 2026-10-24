package com.example.myapplication.componentes

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.JsonData
import com.example.myapplication.dtos.User

class logicaLogin: ViewModel() {
    var rutInput by mutableStateOf("")
        private  set

    var paswordIput by mutableStateOf("")
        private  set

    var mensajeError by mutableStateOf<String?>(null)
        private  set

    var loginExtoso by mutableStateOf(false)
        private  set

    //Metodos de Actualizacion de los Imputs
    fun actualizaRut(nrut: String){
        rutInput =nrut
        mensajeError=null
    }
    fun actualizaCampoPass(npassword: String){
        paswordIput=npassword
        mensajeError= null
    }

    fun intentarLogin(onSucces: (User)-> Unit){
        if (
            rutInput.isBlank()|| paswordIput.isBlank()
        ){
            mensajeError="por favor completa los campos "
        }
        val credencialEncontrada= JsonData.baseDatosMock.find {
            it.rut.trim()==rutInput.trim()&& it.password==paswordIput
        }
        if (credencialEncontrada!=null){
            loginExtoso= true
        }else{
            mensajeError="Rut o contraseña incorrectas"
        }
    }

}