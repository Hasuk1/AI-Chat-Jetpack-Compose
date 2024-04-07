package com.hassuk1.core.data.repository

import com.hassuk1.core.database.UserDataDao
import com.hassuk1.core.database.UserDataTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val userDataDao: UserDataDao) :
  UserDataRepository {
  override suspend fun saveUserData(userData: UserDataTable) {
    userDataDao.insertUserData(userData)
    userDataDao.updateUserData(userData.id, userData.userKey, userData.selectedApiUrl)
  }

  override suspend fun getUserData(): Flow<UserDataTable?> {
    return userDataDao.getUserData()
  }

}