package com.hassuk1.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
  tableName = "chats", foreignKeys = [ForeignKey(
    entity = UserData::class,
    parentColumns = ["id"],
    childColumns = ["user_id"],
    onDelete = ForeignKey.CASCADE
  )]
)
data class Chats(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  @ColumnInfo(name = "user_id") val userId: Long,
  val name: String,
  val description: String
)