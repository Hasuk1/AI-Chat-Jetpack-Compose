package com.hassuk1.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.hassuk1.core.database.model.Message

@Dao
interface MessagesDao {
  @Query("SELECT * FROM messages WHERE chat_id = :chatId")
  fun getMessagesForChat(chatId: Long): List<Message>
}