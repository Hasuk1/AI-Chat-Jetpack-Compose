package com.hassuk1.core.database.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
  override fun migrate(db: SupportSQLiteDatabase) {
    db.execSQL(
      """
      CREATE TABLE IF NOT EXISTS chats (
      id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
      user_id INTEGER NOT NULL, 
      name TEXT, 
      description TEXT, 
      FOREIGN KEY(user_id) REFERENCES user_data(id) ON DELETE CASCADE)
    """.trimIndent()
    )
    db.execSQL(
      """
      CREATE TABLE IF NOT EXISTS messages (
      id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
      chat_id INTEGER NOT NULL,
      assistant_message TEXT,
      human_message TEXT,
      created_at TEXT DEFAULT CURRENT_TIMESTAMP,
      FOREIGN KEY(chat_id) REFERENCES chats(id) ON DELETE CASCADE)
    """.trimIndent()
    )
  }
}
