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
import com.hassuk1.core.data.repository.UserDataRepository
import com.hassuk1.core.database.UserDataTable
import com.hassuk1.core.model.ApiConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val userRepository: UserDataRepository) :
  ViewModel() {
  private val _state = MutableStateFlow(AuthenticationScreenState())
  val state = _state.asStateFlow()
  private val _isUserDataValid = Channel<Boolean>()
  val isUserDataValid = _isUserDataValid.receiveAsFlow()

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

  fun saveUserData() {
    viewModelScope.launch {
      if (isUserDataValid()) {
        userRepository.saveUserData(
          UserDataTable(
            id = 1,
            selectedApiUrl = _state.value.userSelectedApi.baseUrl,
            userKey = _state.value.userEnteredKey
          )
        )
      }
    }
  }

  private fun getUserData() {
    viewModelScope.launch {
      userRepository.getUserData().collect { userData ->
        Log.d("MyLog", "Api=$userData")
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
      _isUserDataValid.send(true)
      true
    } else {
      _isUserDataValid.send(false)
      false
    }
  }
}

