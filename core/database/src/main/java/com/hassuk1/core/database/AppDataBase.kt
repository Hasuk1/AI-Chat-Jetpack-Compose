package com.hassuk1.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDataTable::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun userDao(): UserDataDao
}