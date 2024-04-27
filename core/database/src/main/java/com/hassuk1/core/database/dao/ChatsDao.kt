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
  @Query("SELECT * FROM chats WHERE user_id = :userId ORDER BY id")
  fun getAllChatsAsc(userId: Long): Flow<List<Chat>>

  @Query("SELECT * FROM chats WHERE user_id = :userId ORDER BY id DESC")
  fun getAllChatsDesc(userId: Long): Flow<List<Chat>>

  @Query("SELECT * FROM chats WHERE id = :chatId LIMIT 1")
  fun getChatDataById(chatId:Long):Flow<Chat?>

  @Upsert
  suspend fun addNewChat(chat: Chat): Long

  @Update
  suspend fun updateChatData(chat: Chat)

  @Delete
  suspend fun deleteChat(chat: Chat)
}