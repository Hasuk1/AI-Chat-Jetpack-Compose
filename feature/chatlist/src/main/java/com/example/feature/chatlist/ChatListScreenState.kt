package com.example.feature.chatlist

import com.hassuk1.core.model.Chat

data class ChatListScreenState(
  val chatList: List<Chat> = mutableListOf()
)