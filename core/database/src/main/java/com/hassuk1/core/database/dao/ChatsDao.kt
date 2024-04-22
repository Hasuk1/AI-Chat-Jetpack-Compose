package com.hassuk1.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.hassuk1.core.database.model.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatsDao {
  @Query("SELECT * FROM chats WHERE user_id = :userId ORDER BY id DESC")
  fun getAllChats(userId: Long): Flow<List<Chat>>

  @Upsert
  suspend fun addNewChat(chat: Chat): Long

  @Update
  suspend fun markAsDeleted(chat: Chat)

  @Delete
  suspend fun deleteChat(chat: Chat)
}