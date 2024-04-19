package com.hassuk1.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hassuk1.core.model.ApiConfig

@Entity(tableName = "user_data")
data class UserData(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val selectedApiUrl: String = ApiConfig.NEURO.baseUrl,
  val userKey: String = ""
)