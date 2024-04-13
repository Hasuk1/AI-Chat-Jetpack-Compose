package com.hassuk1.core.data.repository

import com.example.core.common.Resource
import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.ChatCompletionResponseDTO
import com.example.core.network.remote.ModelResponse
import com.hassuk1.core.database.UserDataTable
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
  suspend fun saveUserData(userData: UserDataTable): List<Long>
  suspend fun getUserData(): Flow<UserDataTable?>
  suspend fun checkUserDataWithApi(
    selectedApiUrl: String, userKey: String
  ): Flow<Resource<ModelResponse>>

  suspend fun test(
    requestDTO: ChatCompletionRequestDTO,
    userKey: String
  ): Flow<Resource<ChatCompletionResponseDTO>>
}