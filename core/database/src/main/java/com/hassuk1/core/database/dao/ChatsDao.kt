package com.hassuk1.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.hassuk1.core.database.model.Chats

@Dao
interface ChatsDao {
  @Query("SELECT * FROM chats")
  fun getAllChats(): List<Chats>
}