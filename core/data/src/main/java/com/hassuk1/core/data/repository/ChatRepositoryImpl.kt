package com.hassuk1.core.data.repository

import com.example.core.common.Resource
import com.example.core.network.AiChatApi
import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.ChatCompletionResponseDTO
import com.hassuk1.core.database.dao.ChatsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
  private val chatsDao: ChatsDao,
  private val api: AiChatApi
) : ChatRepository {
  override suspend fun getCompletionToPromt(
    requestDTO: ChatCompletionRequestDTO, userKey: String
  ): Flow<Resource<ChatCompletionResponseDTO>> {
    return flow {
      emit(Resource.Loading())
      try {
        val chatResponse = api.getCompletionToPromt(
          authToken = userKey, chatCompletionRequestDTO = requestDTO
        )
        emit(Resource.Success(chatResponse))
      } catch (e: HttpException) {
        e.printStackTrace()
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        return@flow
      } catch (e: IOException) {
        e.printStackTrace()
        emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        return@flow
      } catch (e: Exception) {
        e.printStackTrace()
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        return@flow
      }
    }
  }
}