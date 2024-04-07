package com.hassuk1.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hassuk1.core.model.ApiConfig

@Entity(tableName = "user_data")
data class UserDataTable (
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val selectedApiUrl: String = ApiConfig.NEURO.baseUrl,
  val userKey: String? = null
)