package com.hassuk1.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hassuk1.core.database.dao.ChatsDao
import com.hassuk1.core.database.dao.MessagesDao
import com.hassuk1.core.database.dao.UserDataDao
import com.hassuk1.core.database.model.Chat
import com.hassuk1.core.database.model.Message
import com.hassuk1.core.database.model.UserData

@Database(
  entities = [UserData::class, Chat::class, Message::class],
  version = 2,
  exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun userDao(): UserDataDao
  abstract fun chatsDao(): ChatsDao
  abstract fun messageDao(): MessagesDao
}