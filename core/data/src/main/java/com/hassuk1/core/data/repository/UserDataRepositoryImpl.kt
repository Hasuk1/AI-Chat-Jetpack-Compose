package com.hassuk1.core.data.repository

import com.example.core.common.Resource
import com.example.core.network.AiChatApi
import com.example.core.network.BaseUrlInterceptor
import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.ChatCompletionResponseDTO
import com.example.core.network.remote.ModelResponse
import com.hassuk1.core.database.UserDataDao
import com.hassuk1.core.database.UserDataTable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.HttpUrl.Companion.toHttpUrl
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
  private val userDataDao: UserDataDao,
  private val api: AiChatApi,
  private val baseUrlInterceptor: BaseUrlInterceptor
) : UserDataRepository {
  override suspend fun saveUserData(userData: UserDataTable): List<Long> {
    baseUrlInterceptor.setNewBaseUrl(userData.selectedApiUrl.toHttpUrl())
    return userDataDao.insertUserData(userData)
  }

  override suspend fun getUserData(): Flow<UserDataTable?> {
    return userDataDao.getUserData()
  }

  override suspend fun checkUserDataWithApi(
    selectedApiUrl: String, userKey: String
  ): Flow<Resource<ModelResponse>> {
    baseUrlInterceptor.setNewBaseUrl(selectedApiUrl.toHttpUrl())
    return flow {
      emit(Resource.Loading())
      try {
        val modelStatus = api.getModelStatus(authToken = "Bearer $userKey")
        emit(Resource.Success(modelStatus))
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



