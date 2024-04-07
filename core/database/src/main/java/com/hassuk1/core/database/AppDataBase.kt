package com.hassuk1.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hassuk1.core.model.UserData

@Database(entities = [UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun userDao(): UserDataDao
}