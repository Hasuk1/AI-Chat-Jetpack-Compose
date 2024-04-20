/*
 * MIT License
 *
 * Copyright (c) "2024" Mikhail Karpushov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hassuk1.feature.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.ErrorType
import com.example.core.common.Resource
import com.example.core.common.Result
import com.example.core.network.dto.ChatCompletionRequestDTO
import com.example.core.network.dto.model.Message
import com.hassuk1.core.data.repository.UserDataRepository
import com.hassuk1.core.database.model.UserData
import com.hassuk1.core.model.ApiConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val userRepository: UserDataRepository) :
  ViewModel() {
  private val _state = MutableStateFlow(AuthenticationScreenState())
  val state = _state.asStateFlow()
  private val _errorType = Channel<ErrorType>()
  val errorType = _errorType.receiveAsFlow()

  init {
    getUserData()
  }

  fun updateSelectedApi(newApi: ApiConfig) {
    viewModelScope.launch {
      _state.value = _state.value.copy(userSelectedApi = newApi)
    }
  }

  fun updateEnteredKey(newKey: String) {
    viewModelScope.launch {
      _state.value = _state.value.copy(userEnteredKey = newKey)
    }
  }

  fun connectAndSaveUserData(
    openDialog: () -> Unit = {},
    closeDialog: () -> Unit = {},
    connectedAction: () -> Unit = {}
  ) {
    viewModelScope.launch {
      if (isUserDataValid()) {
        openDialog()
        userRepository.checkUserDataWithApi(
          selectedApiUrl = _state.value.userSelectedApi.baseUrl,
          userKey = _state.value.userEnteredKey
        ).collectLatest { resource ->
          when (resource) {
            is Resource.Success -> {
              _state.value = _state.value.copy(connectedToApiStatus = Result.SUCCESS)
              _errorType.send(ErrorType.NONE)
              saveUserDate()
              Log.d("MyLog", "Success model ${resource.data}")
              delay(400)
              closeDialog()
              delay(100)
              connectedAction()
            }

            is Resource.Error -> {
              _state.value = _state.value.copy(connectedToApiStatus = Result.ERROR)
              _errorType.send(ErrorType.CONNECTED_FAIL)
              Log.d("MyLog", "Error model ${resource.message}")
            }

            is Resource.Loading -> {
              _state.value = _state.value.copy(connectedToApiStatus = Result.LOADING)
              Log.d("MyLog", "Loading model")
            }
          }
        }
      }
    }
  }

  private suspend fun saveUserDate() {
    userRepository.saveUserData(
      UserData(
        id = 1,
        selectedApiUrl = _state.value.userSelectedApi.baseUrl,
        userKey = _state.value.userEnteredKey
      )
    )
  }

  private fun getUserData() {
    viewModelScope.launch(Dispatchers.IO) {
      userRepository.getUserData().collect { userData ->
        Log.d("MyLog", "AuthenticationViewModel UserData=$userData")
        userData?.let {
          _state.value = _state.value.copy(
            userEnteredKey = userData.userKey,
            userSelectedApi = if (userData.selectedApiUrl == ApiConfig.NEURO.baseUrl) ApiConfig.NEURO else ApiConfig.OPENAI
          )
        }
      }
    }
  }

  private suspend fun isUserDataValid(): Boolean {
    val userEnteredKey = _state.value.userEnteredKey
    return if (userEnteredKey.isNotEmpty() && userEnteredKey.length == 51 && userEnteredKey[2] == '-') {
      _errorType.send(ErrorType.NONE)
      true
    } else {
      _errorType.send(ErrorType.INCORRECT_API)
      false
    }
  }

  fun test() {
    viewModelScope.launch {
      userRepository.getCompletionToPromt(
        requestDTO = ChatCompletionRequestDTO(
          messages = listOf(
            Message(
              content = "Say this is a test", role = "user"
            )
          )
        ), userKey = _state.value.userEnteredKey
      ).collectLatest { resource ->
        when (resource) {
          is Resource.Success -> {
            Log.d("MyLog", "Answer->${resource.data}")
          }

          is Resource.Error -> {
            Log.d("MyLog", "Error->${resource.message}")
          }

          is Resource.Loading -> {
            Log.d("MyLog", "Loading answer")
          }
        }
      }
    }
  }
}
