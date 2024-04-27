package com.hassuk1.core.data.repository

import com.example.core.common.OrderType
import com.hassuk1.core.database.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {
  suspend fun getAllChats(userId: Long, orderBy: OrderType): Flow<List<Chat>>
  suspend fun getChatDataById(chatId: Long): Flow<Chat?>
  suspend fun addNewChat(chat: Chat): Long
  suspend fun updateChatData(chat: Chat)
  suspend fun deleteChat(chat: Chat)
}