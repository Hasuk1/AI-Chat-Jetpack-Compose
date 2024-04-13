package com.example.core.network

import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.ChatCompletionResponseDTO
import com.example.core.network.remote.ModelResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AiChatApi {
  @GET("v1/models")
  suspend fun getModelStatus(
    @Header("Authorization") authToken: String,
  ): ModelResponse

  @POST("v1/chat/completions")
  suspend fun getCompletionToPromt(
    @Header("Content-Type") contentType: String = "application/json",
    @Header("Authorization") authToken: String,
    @Body chatCompletionRequestDTO: ChatCompletionRequestDTO
  ): ChatCompletionResponseDTO
}