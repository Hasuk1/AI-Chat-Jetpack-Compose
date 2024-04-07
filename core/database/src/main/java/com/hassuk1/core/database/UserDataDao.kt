package com.hassuk1.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {
  @Query("SELECT * FROM user_data")
  fun getUserData(): Flow<UserDataTable?>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insertUserData(vararg userData: UserDataTable)

  @Query("UPDATE user_data SET selectedApiUrl = :selectedApiUrl, userKey = :userKey WHERE id = :id")
  suspend fun updateUserData(id: Long, selectedApiUrl: String?, userKey: String)
}