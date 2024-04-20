package com.hassuk1.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
  tableName = "messages", foreignKeys = [ForeignKey(
    entity = Chat::class,
    parentColumns = ["id"],
    childColumns = ["chat_id"],
    onDelete = ForeignKey.CASCADE
  )]
  ,indices = [Index("chat_id")]
)
data class Message(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  @ColumnInfo(name = "chat_id") val chatId: Long,
  @ColumnInfo(name = "assistant_message") val assistantMessage: String,
  @ColumnInfo(name = "human_message") val humanMessage: String,
  @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP") val createdAt: Date = Date()
)