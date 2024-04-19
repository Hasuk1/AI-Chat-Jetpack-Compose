package com.hassuk1.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hassuk1.core.database.dao.ChatsDao
import com.hassuk1.core.database.dao.MessagesDao
import com.hassuk1.core.database.dao.UserDataDao
import com.hassuk1.core.database.model.Chats
import com.hassuk1.core.database.model.Messages
import com.hassuk1.core.database.model.UserData

@Database(entities = [UserData::class, Chats::class, Messages::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
  abstract fun userDao(): UserDataDao
  abstract fun chatDao(): ChatsDao
  abstract fun messageDao(): MessagesDao
}