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
fun mensajes(chats: List<Chats>, onChatClik: (Chats) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red = 242, green = 242, blue = 242)),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(all = 40.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chats) { chat ->
                ChatCard(
                    chat = chat,
                    onClick = { onChatClik(chat) }
                )
            }
        }
    }
}