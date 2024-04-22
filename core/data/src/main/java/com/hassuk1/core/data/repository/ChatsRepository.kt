package com.hassuk1.core.data.repository

import com.hassuk1.core.database.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {
  suspend fun getAllChats(userId: Long): Flow<List<Chat>>
  suspend fun addNewChat(chat: Chat): Long
  suspend fun markAsDeleted(chat: Chat)
  suspend fun deleteChat(chat: Chat)
}