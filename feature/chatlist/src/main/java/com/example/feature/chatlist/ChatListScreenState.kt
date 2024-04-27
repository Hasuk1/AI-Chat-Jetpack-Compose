package com.example.feature.chatlist

import com.hassuk1.core.database.model.Chat
import com.hassuk1.core.database.model.UserData

data class ChatListScreenState(
  val chatList: List<Chat> = emptyList(),
  val userId: Long = 0,
  var newChatBottomSheetOpen: Boolean = false,
  var orderSettingsDropdownMenuOpen: Boolean = false
)