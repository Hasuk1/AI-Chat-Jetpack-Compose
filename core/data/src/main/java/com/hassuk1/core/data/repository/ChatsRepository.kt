package com.hassuk1.core.data.repository

import androidx.lifecycle.LiveData
import com.hassuk1.core.database.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {
  suspend fun getAllChats(userId:Long): LiveData<List<Chat>>
  suspend fun addNewChat(userId: Long, name: String, description: String):Long
  suspend fun deleteChat(id: Long)
}