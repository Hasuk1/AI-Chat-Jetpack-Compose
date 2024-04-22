package com.hassuk1.core.data.repository

import androidx.lifecycle.LiveData
import com.hassuk1.core.database.dao.ChatsDao
import com.hassuk1.core.database.model.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ChatsRepositoryImpl @Inject constructor(private val chatsDao: ChatsDao) : ChatsRepository {
  override suspend fun getAllChats(userId: Long): Flow<List<Chat>> =
    chatsDao.getAllChats(userId)

  override suspend fun addNewChat(chat: Chat): Long =
    chatsDao.addNewChat(chat)

  override suspend fun markAsDeleted(chat: Chat) =
    chatsDao.markAsDeleted(chat)


  override suspend fun deleteChat(chat: Chat) = chatsDao.deleteChat(chat)
}