package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun mensajes(){
    Box(
        Modifier.fillMaxSize()
            .background(Color(242,242,242)),
         Alignment.Center
    ){
        LazyColumn(

        )
        { }
    }

}