package com.hassuk1.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "chats", foreignKeys = [ForeignKey(
    entity = UserData::class,
    parentColumns = ["id"],
    childColumns = ["user_id"],
    onDelete = ForeignKey.CASCADE
  )], indices = [Index("user_id")]
)
data class Chat(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  @ColumnInfo(name = "user_id") val userId: Long,
  val name: String,
  val description: String,
  @ColumnInfo(name = "is_visible") val isVisible: Boolean = true
)