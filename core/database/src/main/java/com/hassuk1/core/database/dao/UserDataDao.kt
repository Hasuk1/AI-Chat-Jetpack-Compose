package com.hassuk1.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hassuk1.core.database.model.UserData
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDataDao {
  @Query("SELECT * FROM user_data")
  fun getUserData(): Flow<UserData?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUserData(vararg userData: UserData): List<Long>
}