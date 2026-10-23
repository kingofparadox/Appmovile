package com.example.myapplication.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.myapplication.componentes.ChatCard
import com.example.myapplication.dtos.Chats

@Composable
fun mensajes(chats: List<Chats>, onChatClik: (Chats)-> Unit){
    Box(
        Modifier.fillMaxSize()
            .background(Color(242,242,242)),
         Alignment.Center
    ){
        LazyColumn(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            items(chats){
                chat->
                ChatCard(chat, {onChatClik(chat)})
            }
        }
    }

}