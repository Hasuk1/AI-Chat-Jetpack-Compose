package com.hassuk1.core.data.repository

import com.example.core.common.Resource
import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.ChatCompletionResponseDTO
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
  suspend fun getCompletionToPromt(
    requestDTO: ChatCompletionRequestDTO,
    userKey: String
  ): Flow<Resource<ChatCompletionResponseDTO>>
}