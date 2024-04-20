package com.hassuk1.core.data.repository

import com.example.core.common.Resource
import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.ChatCompletionResponseDTO
import com.example.core.network.remote.ModelResponse
import com.hassuk1.core.database.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
  suspend fun saveUserData(userData: UserData): List<Long>
  suspend fun getUserData(): Flow<UserData?>

  suspend fun checkUserDataWithApi(
    selectedApiUrl: String, userKey: String
  ): Flow<Resource<ModelResponse>>

  suspend fun getCompletionToPromt(
    requestDTO: ChatCompletionRequestDTO,
    userKey: String
  ): Flow<Resource<ChatCompletionResponseDTO>>
}