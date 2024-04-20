package com.hassuk1.core.data.repository

import androidx.lifecycle.LiveData
import com.hassuk1.core.database.dao.ChatsDao
import com.hassuk1.core.database.model.Chat
import javax.inject.Inject


class ChatsRepositoryImpl @Inject constructor(private val chatsDao: ChatsDao) : ChatsRepository {
  override suspend fun getAllChats(userId: Long): LiveData<List<Chat>> =
    chatsDao.getAllChats(userId)

  override suspend fun addNewChat(userId: Long, name: String, description: String): Long =
    chatsDao.addNewChat(userId, name, description)

  override suspend fun deleteChat(id: Long) = chatsDao.deleteChat(id)
}