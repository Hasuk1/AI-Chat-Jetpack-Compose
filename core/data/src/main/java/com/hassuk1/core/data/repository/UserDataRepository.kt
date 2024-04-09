package com.hassuk1.core.data.repository

import com.hassuk1.core.database.UserDataTable
import com.hassuk1.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
  suspend fun saveUserData(userData: UserDataTable):List<Long>
  suspend fun getUserData(): Flow<UserDataTable?>
}