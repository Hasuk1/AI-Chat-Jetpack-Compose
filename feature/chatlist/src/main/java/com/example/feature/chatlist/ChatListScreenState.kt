package com.example.feature.chatlist

import com.hassuk1.core.database.model.Chat
import com.hassuk1.core.database.model.UserData

data class ChatListScreenState(
  val chatList: List<Chat> = emptyList(),
  val userData: UserData = UserData(),
  var newChatBottomSheetOpen: Boolean = false,
)