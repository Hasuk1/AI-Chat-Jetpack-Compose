package com.hassuk1.core.data.repository

import com.example.core.common.OrderType
import com.hassuk1.core.database.dao.ChatsDao
import com.hassuk1.core.database.model.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatsRepositoryImpl @Inject constructor(private val chatsDao: ChatsDao) : ChatsRepository {
  override suspend fun getAllChats(userId: Long, orderBy: OrderType): Flow<List<Chat>> {
    return when (orderBy) {
      OrderType.NEWEST -> chatsDao.getAllChatsDesc(userId)
      OrderType.OLDEST -> chatsDao.getAllChatsAsc(userId)
    }
  }

  override suspend fun getChatDataById(chatId: Long): Flow<Chat?> {
    return chatsDao.getChatDataById(chatId)
  }

  override suspend fun addNewChat(chat: Chat): Long =
    chatsDao.addNewChat(chat)

  override suspend fun updateChatData(chat: Chat) =
    chatsDao.updateChatData(chat)

  override suspend fun deleteChat(chat: Chat) = chatsDao.deleteChat(chat)
}