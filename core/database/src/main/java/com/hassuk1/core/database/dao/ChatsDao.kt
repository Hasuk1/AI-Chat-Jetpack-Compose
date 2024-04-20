package com.hassuk1.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.hassuk1.core.database.model.Chat
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.LiveData

@Dao
interface ChatsDao {
  @Query("SELECT * FROM chats WHERE user_id = :userId ORDER BY id DESC")
  fun getAllChats(userId: Long): LiveData<List<Chat>>

  @Query("INSERT INTO chats (user_id, name, description) VALUES (:userId,:name, :description)")
  suspend fun addNewChat(userId: Long, name: String, description: String): Long

  @Query("DELETE FROM chats WHERE id = :id")
  suspend fun deleteChat(id: Long)
}