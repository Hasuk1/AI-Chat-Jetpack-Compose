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

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUserData(vararg userData: UserDataTable):List<Long>
}