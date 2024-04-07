package com.hassuk1.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hassuk1.core.model.UserData
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {
  @Query("SELECT * FROM user_data LIMIT 1")
  suspend fun getUserData(): Flow<UserData?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveUserData(userData: UserData)
}